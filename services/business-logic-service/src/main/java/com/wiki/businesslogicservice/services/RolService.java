package com.wiki.businesslogicservice.services;

import com.wiki.businesslogicservice.repositories.RolRepository;
import models.Rol;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class RolService implements BaseService<Rol>{

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }
    @Override
    public Page<Rol> findAll(int page, int size) {
        return null;
    }

    @Override
    public Rol create(Rol objeto) {

        return null;
    }

    @Override
    public Rol findById(Number id) {
        return null;
    }

    public Rol findByNombre(String nombre){
        Optional<Rol> result = rolRepository.findByNomRol(nombre);
        if (result.isPresent()){
            return result.get();
        }
        throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, String.format("The ROL %s not found", nombre));
    }
    @Override
    public Rol update(Rol objeto) {
        return null;
    }

    @Override
    public void delete(Rol objeto) {

    }
}
