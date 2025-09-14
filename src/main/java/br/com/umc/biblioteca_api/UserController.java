package br.com.umc.biblioteca_api;

import br.com.umc.biblioteca_api.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileStorageService fileStorageService;

    // A assinatura do método agora inclui o 'cpf' como texto
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("cpf") String cpf, // Novo campo CPF
            @RequestParam("document") MultipartFile documentFile
    ) {
        // --- VALIDAÇÃO MANUAL DO CPF ---
        // Já que não estamos usando @Valid, fazemos a validação aqui
        if (cpf == null || !Pattern.matches("^\\d{11}$", cpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de CPF inválido. Deve conter 11 dígitos.");
        }
        // --------------------------------

        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este e-mail já está em uso.");
        }

        String documentFileName = fileStorageService.storeFile(documentFile);

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);

        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);

        // Salva o CPF no campo 'cpf'
        newUser.setCpf(cpf);
        // Salva o NOME DO ARQUIVO no campo 'documentUrl'
        newUser.setDocumentUrl(documentFileName);

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }
}