package br.com.anderson.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.anderson.entities.Usuario;
import br.com.anderson.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            if (repository.findByUsername("admin") == null) {

                Usuario user = new Usuario();
                user.setUsername("admin");
                user.setRole("ADMIN");
                user.setPassword(passwordEncoder.encode("confeitaria123#"));

                repository.save(user);

                System.out.println("Usuário admin criado com sucesso!");
            }
        };
    }
}
