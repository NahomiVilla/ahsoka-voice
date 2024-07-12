package com.ashokavoice.ashokavoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
    public Optional<Users> editarPerfil(Long idUsuario,Users nuevosDatos){
        return usersRepository.findById(idUsuario).map(user -> {
            user.setNombre(nuevosDatos.getNombre());
            user.setNombreUsuario(nuevosDatos.getNombreUsuario());
            return usersRepository.save(user);
        });
    }

    public Optional<Users> editarContrasena(Long idUsuario,String nuevaContraseña){
        return usersRepository.findById(idUsuario).map(user->{
            user.setContrasena(passwordEncoder.encode(nuevaContraseña));
            return usersRepository.save(user);
        });
    }

    public Optional<Users> actualizarFotoPerfil(Long idUsuario,String nuevaFoto){
        return usersRepository.findById(idUsuario).map(usuario->{
            usuario.setFotoPerfil(nuevaFoto);
            return usersRepository.save(usuario);
        });
    }

    public boolean eliminarCuenta(Long idUsuario){
        return usersRepository.findById(idUsuario).map(usuario->{
            usersRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
