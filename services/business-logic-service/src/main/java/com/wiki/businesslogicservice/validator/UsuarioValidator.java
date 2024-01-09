package com.wiki.businesslogicservice.validator;

import com.wiki.businesslogicservice.repositories.UsuarioRepository;
import models.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {
    private final UsuarioRepository usuarioRepository;

    public UsuarioValidator(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario isPresent(Optional<Usuario> result, String parameter){
        if (result.isPresent()){
            return result.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El usuario no fue encontrado, para el parametro %s", parameter));
    }
    public boolean authenticateUser(String email, String password) { //para login
        Optional<Usuario> result = usuarioRepository.findByEmail(email);
        return result.filter(usuario -> BCrypt.checkpw(password, usuario.getPassword())).isPresent();
    }

    public boolean emailValidateFormat(String email){
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public boolean emailValidateExist(String email){
        Optional<String> result = usuarioRepository.findEmail(email);
        return result.isPresent();
    }
    public  boolean emailValidate(String email){
        return  !email.isBlank() && 7 < email.length() && email.length() < 51;
    }

    public boolean nombreValidate(String nombre){
        return  !nombre.isBlank() && 2 < nombre.length() && nombre.length() < 15;
    }

    public boolean apellidoValidate(String apellido){
        return  !apellido.isBlank() && 1 < apellido.length() && apellido.length() < 16;
    }

    public boolean passConfirmValidate(Usuario usuario){
        return usuario.getPassword().equals(usuario.getPasswordConfirmation());
    }
    public boolean passValidate(String pass){
        return  !pass.isBlank() && pass.length() > 7;
    }
    public List<ObjectError> userValidate(Usuario usuario){
        List<ObjectError> erroresValid = new ArrayList<>();

        if (!emailValidateFormat(usuario.getEmail())){
            erroresValid.add(new ObjectError("emailFormat", "Error en formato del correo"));
        }
        if (!emailValidate(usuario.getEmail())){
            erroresValid.add(new ObjectError("emailLength", "Error en el largo del correo (Min 10 y Max 30 Caracteres) "));
        }
        if (!apellidoValidate(usuario.getApellido())){
            erroresValid.add(new ObjectError("apellidoLength", "Error en el largo del Apellido (Min 10 y Max 30 Caracteres) "));
        }
        if (!nombreValidate(usuario.getNombre())){
            erroresValid.add(new ObjectError("nombreLength", "Error en el largo del Nombre (Min 10 y Max 30 Caracteres) "));
        }
        if (!passValidate(usuario.getPassword())){
            erroresValid.add(new ObjectError("passLength", "Error en el largo de la Password (Min 10 y Max 30 Caracteres) "));
        }
        if (!passConfirmValidate(usuario)){
            erroresValid.add(new ObjectError("passConfirm", "Error en el largo de la Password (Min 10 y Max 30 Caracteres) "));
        }
        return erroresValid;
    }
}
