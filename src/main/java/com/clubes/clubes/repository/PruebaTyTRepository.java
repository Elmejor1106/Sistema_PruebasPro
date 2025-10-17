package com.clubes.clubes.repository;

import com.clubes.clubes.entidades.PruebaTyT;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PruebaTyTRepository extends MongoRepository<PruebaTyT, String> {
    java.util.List<PruebaTyT> findByUsuarioId(String usuarioId);
    PruebaTyT findByNumeroRegistro(String numeroRegistro);
}
