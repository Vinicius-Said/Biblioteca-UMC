package br.com.umc.biblioteca_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // Anotação do Lombok para criar getters, setters, etc. automaticamente
@Entity // Diz ao JPA que esta classe é uma entidade do banco de dados
@Table(name = "users") // Especifica o nome da tabela
public class User {

    @Id // Marca o campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz que o ID é autogerado pelo banco
    private Long id;

    private String name;
    private String email;
    private String password; // ATENÇÃO: Por simplicidade, vamos salvar a senha como texto puro por agora. O próximo passo seria usar hash!
    private String document;
}