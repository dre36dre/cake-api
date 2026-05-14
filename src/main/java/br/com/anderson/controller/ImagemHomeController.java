package br.com.anderson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.anderson.entities.ImagemHome;
import br.com.anderson.repository.ImagemHomeRepository;
import br.com.anderson.service.ImagemService;

@RestController
@RequestMapping("/imagens-home")
@CrossOrigin("*")
public class ImagemHomeController {

    @Autowired
    private ImagemHomeRepository repository;

    @Autowired
    private ImagemService imagemService;   // ✅ AGORA EXISTE

    @GetMapping
    public List<ImagemHome> listar() {
        return repository.findAll(Sort.by("ordem"));
    }

    @PutMapping("/{id}")
    public ImagemHome atualizar(@PathVariable Long id, @RequestBody ImagemHome img) {
        img.setId(id);
        return repository.save(img);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String nome = imagemService.salvar(file);
        String url = "https://cake-api-production.up.railway.app/imagens/" + nome;
        return ResponseEntity.ok(url);
    }
}
