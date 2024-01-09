package com.wiki.businesslogicservice.controllers;

import com.wiki.businesslogicservice.services.UsuarioService;
import com.wiki.businesslogicservice.validator.UsuarioValidator;
import jakarta.validation.Valid;
import models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioControllers {
    private final UsuarioService usuarioService;

    private final UsuarioValidator usuarioValidator;

    public UsuarioControllers(UsuarioService usuarioService,UsuarioValidator usuarioValidator ){
        this.usuarioService = usuarioService;
        this.usuarioValidator = usuarioValidator;
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUser(@Valid Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(null);
        }

        return  new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Usuario> updateUser(@Valid Usuario usuario, BindingResult result){
         //usuarioValidator.userValidate(usuario).forEach(result::addError);
        if (result.hasErrors()) {
            // Aquí puedes manejar los errores adicionales si es necesario
            return ResponseEntity.badRequest().body(null);
        }
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.OK);
    }
}
