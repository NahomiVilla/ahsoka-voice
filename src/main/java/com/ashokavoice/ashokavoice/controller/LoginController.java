package com.ashokavoice.ashokavoice.controller;

import java.util.Optional;

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

    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> autenticarUsuario(@RequestBody Users login) {
        Optional<Users> usuarioValidado = loginService.validarUsuario(login.getNombreUsuario(), login.getContraseña());
        if (usuarioValidado.isPresent()) {
            return ResponseEntity.ok("Autenticación exitosa");
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

}
