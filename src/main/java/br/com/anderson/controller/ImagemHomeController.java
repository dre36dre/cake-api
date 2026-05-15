package br.com.anderson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import br.com.anderson.entities.ImagemHome;
import br.com.anderson.repository.ImagemHomeRepository;

@RestController
@RequestMapping("/imagens-home")
@CrossOrigin("*")
public class ImagemHomeController {

    @Autowired
    private ImagemHomeRepository repository;

    @GetMapping
    public List<ImagemHome> listar() {
        return repository.findAll(Sort.by("ordem"));
    }

    @PostMapping
    public ImagemHome salvar(@RequestBody ImagemHome img) {
        return repository.save(img);
    }

    @PutMapping("/{id}")
    public ImagemHome atualizar(@PathVariable Long id, @RequestBody ImagemHome img) {
        img.setId(id);
        return repository.save(img);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
