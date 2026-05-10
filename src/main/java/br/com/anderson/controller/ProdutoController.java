package br.com.anderson.controller;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.anderson.entities.Produto;
import br.com.anderson.service.ProdutoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    // Pasta onde as imagens serão salvas (dentro do projeto)
    private final Path pastaUpload = Paths.get("src/main/resources/static/imagens/");

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // Criar produto
    @PostMapping
    public Produto create(@RequestBody @Valid Produto produto) {
        return service.create(produto);
    }

    // Listar produtos
    @GetMapping
    public List<Produto> listAll() {
        return service.findAll();
    }

    // Upload de imagem
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            Files.createDirectories(pastaUpload);

            Path destino = pastaUpload.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            // URL pública que o Angular vai usar
            String url = "/imagens/" + file.getOriginalFilename();

            return ResponseEntity.ok(url);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar imagem: " + e.getMessage());
        }
    }

    // Servir imagem
    @GetMapping("/imagens/{nome:.+}")
    public ResponseEntity<Resource> imagem(@PathVariable String nome) {
        try {
            Path caminho = pastaUpload.resolve(nome).normalize();
            Resource imagem = new UrlResource(caminho.toUri());

            if (!imagem.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaTypeFactory.getMediaType(nome).orElse(MediaType.IMAGE_JPEG))
                    .body(imagem);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
