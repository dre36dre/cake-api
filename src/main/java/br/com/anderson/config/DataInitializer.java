package br.com.anderson.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.anderson.entities.User;
import br.com.anderson.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            if (repository.findByEmail("admin@email.com").isEmpty()) {

                User user = new User();
                user.setEmail("admin@email.com");
                user.setName("Admin");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRole("ADMIN");

                repository.save(user);

                System.out.println("Usuário admin criado!");
            }
        };
    }
}