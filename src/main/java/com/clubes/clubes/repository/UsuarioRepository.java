package com.clubes.clubes.repository;

import com.clubes.clubes.entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByNumeroDocumento(String numeroDocumento);
    Usuario findByCorreo(String correo);
}
