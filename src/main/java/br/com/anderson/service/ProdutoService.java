package br.com.anderson.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.anderson.dto.CakeDTO;
import br.com.anderson.entities.Produto;
import br.com.anderson.exception.ResourceNotFoundException;
import br.com.anderson.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.anderson.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto create(Produto produto) {

        if (produto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

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

    public Produto update(Long id, Produto updatedCake) {

        Produto existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto not found"));

        existing.setName(updatedCake.getName());
        existing.setDescription(updatedCake.getDescription());
        existing.setPrice(updatedCake.getPrice());
        existing.setAvailable(updatedCake.getAvailable());

        return repository.save(existing);
    }

    public Produto fromDTO(CakeDTO dto) {
        Produto produto = new Produto();
        produto.setName(dto.getName());
        produto.setDescription(dto.getDescription());
        produto.setPrice(dto.getPrice());
        return produto;
    }
}
