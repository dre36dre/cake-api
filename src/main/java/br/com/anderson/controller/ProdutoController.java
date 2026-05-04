package br.com.anderson.controller;

import java.util.List;

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

    // Listar todos
    @GetMapping
    public List<Produto> listAll() {
        return service.findAll();
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
