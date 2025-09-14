package br.com.umc.biblioteca_api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar um usuário pelo e-mail (usado para evitar duplicatas)
    Optional<User> findByEmail(String email);
}