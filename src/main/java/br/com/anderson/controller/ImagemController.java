package br.com.anderson.controller;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @GetMapping("/{nome:.+}")
    public ResponseEntity<Resource> imagem(@PathVariable String nome) {
        ClassPathResource imagem = new ClassPathResource("static/imagens/" + nome);

        if (!imagem.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagem);
    }
}
