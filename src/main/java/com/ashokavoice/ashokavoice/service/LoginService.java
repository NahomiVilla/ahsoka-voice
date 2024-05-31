package com.ashokavoice.ashokavoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class LoginService {
 
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Users> validarUsuario(String nombreUsuario, String contraseña) {
        Optional<Users> user = usersRepository.findByNombreUsuario(nombreUsuario);
        if (user.isPresent() && passwordEncoder.matches(contraseña, user.get().getContraseña())) {
            return user;
        }
        return Optional.empty();
    }
}
