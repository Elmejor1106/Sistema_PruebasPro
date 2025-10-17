package com.clubes.clubes;

import com.clubes.clubes.entidades.Rol;
import com.clubes.clubes.entidades.Usuario;
import com.clubes.clubes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ClubesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (usuarioRepository.findByNumeroDocumento("coordinador") == null) {
				Usuario coordinador = new Usuario();
				coordinador.setNumeroDocumento("coordinador");
				coordinador.setContrasena(passwordEncoder.encode("admin123"));
				coordinador.setRol(Rol.COORDINADOR);
				coordinador.setPrimerNombre("Admin");
				usuarioRepository.save(coordinador);
			}
		};
	}

}
