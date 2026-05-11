package br.com.anderson.service;


import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.anderson.dto.CakeDTO;
import br.com.anderson.entities.Produto;
import br.com.anderson.exception.ResourceNotFoundException;
import br.com.anderson.repository.ProdutoRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@Service
public class ProdutoService {

    private final ProdutoRepository repository;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto create(Produto produto) {

        if (produto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        normalizarImagem(produto);

        return repository.save(produto);
    }


    public List<Produto> findAll() {
        return repository.findAll();
    }
    

    public Produto findById(Long id) {
        return repository.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Produto not found"));
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Produto update(Long id, Produto updatedProduto) {
        Produto existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto not found"));

        existing.setName(updatedProduto.getName());
        existing.setDescription(updatedProduto.getDescription());
        existing.setPrice(updatedProduto.getPrice());
        existing.setAvailable(updatedProduto.getAvailable());

        if (updatedProduto.getImageUrl() != null && !updatedProduto.getImageUrl().isBlank()) {
            existing.setImageUrl(caminhoImagem(updatedProduto.getImageUrl()));
        }

        existing.setUpdatedAt(LocalDateTime.now());

        return repository.save(existing);
    }



    public Produto fromDTO(CakeDTO dto) {
        Produto produto = new Produto();
        produto.setName(dto.getName());
        produto.setDescription(dto.getDescription());
        produto.setPrice(dto.getPrice());
        return produto;
    }

    private void normalizarImagem(Produto produto) {
        if (produto.getImageUrl() != null && !produto.getImageUrl().isBlank()) {
            produto.setImageUrl(caminhoImagem(produto.getImageUrl()));
        }
    }

    private String caminhoImagem(String imageUrl) {
        String nomeArquivo = Paths.get(imageUrl).getFileName().toString();

        return "/imagens/" + nomeArquivo;
    }
    
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
