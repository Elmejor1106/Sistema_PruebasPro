package com.clubes.clubes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/usuarios/login", "/css/**", "/js/**", "/error").permitAll()
                .requestMatchers("/usuarios/registro", "/registro_prueba", "/lista_pruebas", "/editar_prueba/**", "/detalle_prueba/**").hasRole("COORDINADOR")
                .requestMatchers("/usuarios/perfil").hasRole("ESTUDIANTE")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/usuarios/login")
                .loginProcessingUrl("/login")
                .usernameParameter("numeroDocumento")
                .passwordParameter("contrasena")
                .defaultSuccessUrl("/inicio", true)
                .failureUrl("/usuarios/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/usuarios/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
