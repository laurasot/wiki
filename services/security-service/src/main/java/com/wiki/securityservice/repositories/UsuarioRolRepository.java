package com.wiki.securityservice.repositories;

import models.Usuario;
import models.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Number> {
    @Query("SELECT u.usuario FROM UsuarioRol u where u.rol.nomRol = ?1")
    List<Usuario> findUserInRoleName(String roleName);

    List<UsuarioRol> findByUsuario(Usuario user);
}
