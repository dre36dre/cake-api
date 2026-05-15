package br.com.anderson.controller;

import java.nio.file.Path;

import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.anderson.service.ImagemService;

@RestController
@RequestMapping("/imagens")
@CrossOrigin("*")
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String nome = imagemService.salvar(file);
        return ResponseEntity.ok("https://cake-api-production.up.railway.app/imagens/" + nome);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Resource> getImagem(@PathVariable String nome) {
        try {
            Path caminho = imagemService.carregar(nome);
            Resource resource = new UrlResource(caminho.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaTypeFactory.getMediaType(nome).orElse(MediaType.IMAGE_JPEG))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
