package br.com.umc.biblioteca_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // Marca a classe como um controlador REST
@RequestMapping("/api/users") // Define o prefixo da URL para todos os endpoints nesta classe
public class UserController {

    @Autowired // Injeção de dependência: o Spring nos dá uma instância do UserRepository
    private UserRepository userRepository;

    // Mapeia este metodo para requisições POST em /api/users/register
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        // 1. Verificar se o e-mail já existe
        Optional<User> userExists = userRepository.findByEmail(newUser.getEmail());
        if (userExists.isPresent()) {
            // Retorna um erro 409 Conflict se o e-mail já estiver em uso
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este e-mail já está em uso.");
        }

        // TODO: Implementar o HASH da senha aqui antes de salvar!
        // Por exemplo, usando a classe BCryptPasswordEncoder do Spring Security.

        // 2. Salvar o novo usuário no banco de dados
        userRepository.save(newUser);

        // 3. Retornar uma resposta de sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}