package com.ashokavoice.ashokavoice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Users> validarUsuario(String nombreUsuario, String contrasena) {
        logger.info("Validando usuario: {}", nombreUsuario);
        Optional<Users> user = usersRepository.findByNombreUsuario(nombreUsuario);
        
        if (user.isPresent() && passwordEncoder.matches(contrasena, user.get().getContrasena())) {
            logger.info("Usuario autenticado: {}", nombreUsuario);
            return user;
        }
        logger.warn("Autenticación fallida para usuario: {}", nombreUsuario);
        return Optional.empty();
    }
}
