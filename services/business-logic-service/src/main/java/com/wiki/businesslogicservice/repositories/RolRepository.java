package com.wiki.businesslogicservice.repositories;

import models.Rol;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends BaseRepository<Rol>{

    Optional<Rol> findByNomRol(String name);
}
