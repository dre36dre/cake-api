package br.com.anderson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.anderson.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
