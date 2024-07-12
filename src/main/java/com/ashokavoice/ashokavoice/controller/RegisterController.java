package com.ashokavoice.ashokavoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Registers;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.service.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<Registers> registrarUsuario(@RequestBody Registers registers) {
        logger.debug("Recibida solicitud de registro: " + registers.toString());
        try {
            Registers registrado = registerService.registrarUsuario(registers);
            return ResponseEntity.ok(registrado);
        } catch (IllegalArgumentException e) {
            logger.error("Error en el registro: " + e.getMessage());
            return ResponseEntity.badRequest().body(null); // Manejo de error de edad insuficiente
        }
    }

    @GetMapping("/confirmar")
    public ResponseEntity<Users> confirmarRegistro(@RequestParam Integer token1) {
        logger.debug("Recibida solicitud de confirmación de registro con token: " + token1);
        try {
            Users users = registerService.confirmarRegistro(token1);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            logger.error("Error en la confirmación del registro: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}