package com.ashokavoice.ashokavoice.controller;

import com.ashokavoice.ashokavoice.model.Users;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PutMapping("/editarPerfil/{idUsuario}")
    public ResponseEntity<Users> editarPerfil(@PathVariable Long idUsuario, @RequestBody Users users) {
        Optional<Users> usuarioEditado = usersService.editarPerfil(idUsuario, users);
        return usuarioEditado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editarContrasena/{idUsuario}")
    public ResponseEntity<Users> editarContrasena(@PathVariable Long idUsuario, @RequestBody String nuevaContrasena) {
        Optional<Users> usuarioEditado = usersService.editarContrasena(idUsuario, nuevaContrasena);
        return usuarioEditado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/fotoPerfil/{idUsuario}")
    public ResponseEntity<Users> actualizarFotoPerfil(@PathVariable Long idUsuario, @RequestBody String nuevaFotoPerfil) {
        Optional<Users> usuarioEditado = usersService.actualizarFotoPerfil(idUsuario, nuevaFotoPerfil);
        return usuarioEditado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminarCuenta/{idUsuario}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long idUsuario) {
        if (usersService.eliminarCuenta(idUsuario)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}