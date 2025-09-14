package br.com.umc.biblioteca_api.service;

import br.com.umc.biblioteca_api.exceptions.FileValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService() {
        // ... (o construtor continua igual)
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório para armazenar os arquivos.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // --- INÍCIO DA VALIDAÇÃO ---
        if (file.isEmpty()) {
            throw new FileValidationException("O arquivo não pode estar vazio.");
        }

        // Verifica se o content type é de um PDF
        if (!"application/pdf".equals(file.getContentType())) {
            throw new FileValidationException("Formato de arquivo inválido! Por favor, anexe um PDF.");
        }
        // --- FIM DA VALIDAÇÃO ---

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (originalFileName.contains("..")) {
                throw new FileValidationException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + originalFileName);
            }

            String fileExtension = ".pdf"; // Já que validamos, podemos assumir a extensão
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível armazenar o arquivo " + originalFileName + ". Por favor, tente novamente!", ex);
        }
    }
}