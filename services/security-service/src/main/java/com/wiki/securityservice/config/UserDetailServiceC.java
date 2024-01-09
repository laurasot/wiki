package com.wiki.securityservice.config;

import com.wiki.securityservice.repositories.UsuarioRepository;
import com.wiki.securityservice.repositories.UsuarioRolRepository;
import models.Usuario;
import models.UsuarioRol;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceC implements UserDetailsService {
    private final UsuarioRepository  usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioRolRepository usuarioRolRepository;


    public UserDetailServiceC(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioRolRepository usuarioRolRepository){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = usuarioRepository.findByNombre(username);
        if (optional.isPresent()){
            Usuario usuario = optional.get();
            List<UsuarioRol> usuarioRoles = usuarioRolRepository.findByUsuario(usuario);
            String[] roles = usuarioRoles.stream()
                    .map(r -> r.getRol().getNomRol())
                    .toArray(String[]::new);
            return org.springframework.security.core.userdetails.User.withUsername(usuario.getNombre())
                    .password(passwordEncoder.encode(usuario.getPassword())).roles(roles).build();
        }else{
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

    }
}
