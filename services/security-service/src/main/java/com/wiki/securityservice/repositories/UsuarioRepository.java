package com.wiki.securityservice.repositories;

import models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Number> {
    @Query("SELECT u.nombre FROM Usuario u")
    Page<String> findNombre(Pageable pageable);

    Optional<Usuario> findByNombre(String username);

    Optional<Usuario> findByNombreAndPassword(String username, String password);
}
