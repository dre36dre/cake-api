package br.com.anderson.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {

    private final Path pasta = Paths.get("/app/imagens");

    public String salvar(MultipartFile file) {
        try {
            Files.createDirectories(pasta);

            Path destino = pasta.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), destino, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return file.getOriginalFilename();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage());
        }
    }

    public Path carregar(String nomeArquivo) {
        try {
            Path caminho = pasta.resolve(nomeArquivo);

            if (!Files.exists(caminho)) {
                throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo);
            }

            return caminho;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar imagem: " + e.getMessage());
        }
    }
}
