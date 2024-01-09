package com.wiki.businesslogicservice.services;

import com.wiki.businesslogicservice.repositories.UsuarioRepository;
import com.wiki.businesslogicservice.validator.UsuarioValidator;
import models.Rol;
import models.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements BaseService<Usuario>{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;
    private final RolService rolService;
    public UsuarioService(UsuarioRepository usuarioRepository, RolService rolService, UsuarioValidator usuarioValidator ){
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.usuarioValidator = usuarioValidator;
    }
    @Override
    public Page<Usuario> findAll(int page, int size) {
        return null;
    }

    public Page<String> finAllNombres(int page, int size){
        return usuarioRepository.findNombre(PageRequest.of(page, size));
    }

    @Override
    public Usuario create(Usuario usuario) {
            switch (usuario.getTipoUsuario()) {
                case 1 -> {
                    Rol role_user = rolService.findByNombre("ROLE_USER");
                    usuario.setRoles(role_user);
                }
                case 2 -> {
                    Rol role_employee = rolService.findByNombre("ROLE_EMPLOYEE");
                    usuario.setRoles(role_employee);
                }
            }
            return registerPassAndSaved(usuario);
    }
    public Usuario registerPassAndSaved(Usuario usuario) {
        String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashed);
        return usuarioRepository.saveAndFlush(usuario);
    }
    public Usuario findByEmail(String email) {
        Optional<Usuario> result = usuarioRepository.findByEmail(email);
        return usuarioValidator.isPresent(result,email);
    }
    @Override
    public Usuario findById(Number id) {
        Optional<Usuario> result = usuarioRepository.findById(id);
        return usuarioValidator.isPresent(result,id.toString());
    }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario usuarioPresent = findById(usuario.getCodUsuario());

        String hashedPass = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuarioPresent.setPassword(hashedPass);

        if(!usuario.getNombre().equals(usuarioPresent.getNombre())){
            String nomFormat = usuario.getNombre().substring(0,1).toUpperCase()+usuario.getNombre().substring(1).toLowerCase();
            usuarioPresent.setNombre(nomFormat);
        }

        if(!usuario.getApellido().equals(usuarioPresent.getApellido())){
            String apFormat = usuario.getApellido().substring(0, 1).toUpperCase()+usuario.getApellido().substring(1).toLowerCase();
            usuarioPresent.setApellido(apFormat);
        }

        if(!usuario.getEmail().equals(usuarioPresent.getEmail())){
            usuarioPresent.setEmail(usuario.getEmail());
        }

        if(!usuarioPresent.equals(usuario)){
            usuarioRepository.saveAndFlush(usuarioPresent);
        }

        return usuarioPresent;
    }

    @Override
    public void delete(Usuario usuario) {
    }
}
