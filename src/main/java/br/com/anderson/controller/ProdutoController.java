package br.com.anderson.controller;

import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.anderson.entities.Produto;
import br.com.anderson.service.ProdutoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto create(@RequestBody @Valid Produto produto) {
        return service.create(produto);
    }

    @GetMapping(produces = "application/json")
    public List<Produto> listAll() {
        return service.findAll();
    }

    @GetMapping("/imagens/{nome:.+}")
    public ResponseEntity<Resource> imagem(@PathVariable String nome) {
        if (nome.contains("..") || nome.contains("/") || nome.contains("\\")) {
            return ResponseEntity.badRequest().build();
        }

        Resource imagem = new ClassPathResource("static/imagens/" + nome);

        if (!imagem.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagem);
    }

    @PostMapping("/inserir")
    public Produto inserir(@RequestBody @Valid Produto produto) {
        return service.create(produto);
    }

    // Deletar por ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
