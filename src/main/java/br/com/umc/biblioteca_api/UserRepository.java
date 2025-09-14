package br.com.umc.biblioteca_api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<TipoDaEntidade, TipoDoId>
public interface UserRepository extends JpaRepository<User, Long> {

    // O Spring Data JPA magicamente cria a consulta para nós baseada no nome do método!
    Optional<User> findByEmail(String email);
}