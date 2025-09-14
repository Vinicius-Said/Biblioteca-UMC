package br.com.umc.biblioteca_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Anotação que torna esta classe um handler global de exceções
public class GlobalExceptionHandler {

    // Este método será chamado sempre que um erro de validação (@Valid) ocorrer
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Garante que a resposta terá o status 400
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Cria um mapa para armazenar os erros no formato "nomeDoCampo": "mensagemDeErro"
        Map<String, String> errors = new HashMap<>();

        // Pega todos os erros de validação da exceção
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Pega o nome do campo que falhou na validação
            String fieldName = ((FieldError) error).getField();
            // Pega a mensagem de erro que definimos na anotação (@Pattern, @NotBlank, etc)
            String errorMessage = error.getDefaultMessage();
            // Adiciona ao mapa
            errors.put(fieldName, errorMessage);
        });


        // Retorna o mapa, que o Spring converterá para JSON
        return errors;
    }
    @ExceptionHandler(FileValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleFileValidationException(FileValidationException ex) {
        Map<String, String> error = new HashMap<>();
        // A chave do erro pode ser "file" ou "document", dependendo do que fizer mais sentido
        error.put("document", ex.getMessage());
        return error;
    }
}