package com.ashokavoice.ashokavoice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> autenticarUsuario(@RequestBody Users login) {
        logger.info("Intento de autenticación para: {}", login.getNombreUsuario());
        Optional<Users> usuarioValidado = loginService.validarUsuario(login.getNombreUsuario(), login.getContrasena());
        if (usuarioValidado.isPresent()) {
            logger.info("Autenticación exitosa para: {}", login.getNombreUsuario());
        
            return ResponseEntity.ok("Autenticación exitosa");
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

}
