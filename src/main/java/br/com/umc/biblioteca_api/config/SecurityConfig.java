package br.com.umc.biblioteca_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Dentro da classe SecurityConfig.java

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        // ADICIONADO: Permite que o Spring Boot exiba as páginas de erro
                        .requestMatchers("/error").permitAll()
                        // Exige autenticação para qualquer outra requisição
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}