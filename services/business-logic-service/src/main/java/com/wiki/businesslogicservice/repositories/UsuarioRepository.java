package com.wiki.businesslogicservice.repositories;

import models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario>{

    @Query("SELECT u.nombre FROM Usuario u")
    Page<String> findNombre(Pageable pageable);

    Optional<Usuario> findByNombre(String username);

    Optional<Usuario> findByEmailAndPassword(String email, String password);

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u.email FROM Usuario u")
    Optional<String> findEmail(String email);
}
