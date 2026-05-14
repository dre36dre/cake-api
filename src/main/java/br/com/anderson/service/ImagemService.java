package br.com.anderson.service;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {

    private final Path pasta = Paths.get("/app/imagens");

    public String salvar(MultipartFile file) throws IOException {

        Files.createDirectories(pasta);

        Path destino = pasta.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return file.getOriginalFilename();
    }

    public Path carregar(String nome) {
        return pasta.resolve(nome);
    }
}
