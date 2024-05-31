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
@RequestMapping("/confirmar")
public class ConfirmacionController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Registers registers) {
        try {
            Registers savedRegisters = registerService.registrarUsuario(registers);
            return ResponseEntity.ok(savedRegisters);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/confirmar")
    public ResponseEntity<?> confirmarRegistro(@RequestParam String token) {
        try {
            Users savedUsers = registerService.confirmarRegistro(token);
            return ResponseEntity.ok(savedUsers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
