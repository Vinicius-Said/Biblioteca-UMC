package br.com.umc.biblioteca_api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    // --- ÚNICA MUDANÇA É ADICIONAR '@Valid' AQUI ---
    public ResponseEntity<String> registerUser(@Valid @RequestBody User newUser) {
        // O RESTO DO CÓDIGO PERMANECE IGUAL

        Optional<User> userExists = userRepository.findByEmail(newUser.getEmail());
        if (userExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este e-mail já está em uso.");
        }

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}