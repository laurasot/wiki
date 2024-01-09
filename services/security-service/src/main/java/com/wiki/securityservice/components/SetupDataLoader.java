package com.wiki.securityservice.components;


import com.wiki.securityservice.repositories.RolRepository;
import com.wiki.securityservice.repositories.UsuarioRepository;
import models.Privilegio;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
    boolean alreadySetup = false;
    private UsuarioRepository userRepository;
    private RolRepository rolRepository;
    public SetupDataLoader(UsuarioRepository userRepository,RolRepository rolRepository){
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;


           models.Privilegio readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        models.Privilegio writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
 
        List<Privilegio> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        models.Rol adminRole = roleRepository.findByName("ROLE_ADMIN");
        models.Usuario user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
     
    }
}
