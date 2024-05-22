package com.ashokavoice.ashokavoice.controller;

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

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody Registers registers) {
        Registers registrado = registerService.registrarUsuario(registers);
        return ResponseEntity.ok(registrado);
    }

    @GetMapping("/confirmar")
    public ResponseEntity<?> confirmarRegistro(@RequestParam String token) {
        Users users = registerService.confirmarRegistro(token);
        return ResponseEntity.ok(users);
    }
}
