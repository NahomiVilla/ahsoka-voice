package com.ashokavoice.ashokavoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.service.RegisterService;

@RestController
@RequestMapping("/confirmar")
public class ConfirmacionController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/token/{tokenConfirmacion}")
    public ResponseEntity<?> confirmacionPorToken(@PathVariable Integer tokenConfirmacion) {
        try {
            Users users = registerService.confirmarRegistro(tokenConfirmacion);
            if (users != null) {
                // Realiza aquí las acciones correspondientes al encontrar el token de confirmación
                return ResponseEntity.ok("Token de confirmación válido");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}