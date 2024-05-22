package com.ashokavoice.ashokavoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Registers;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.RegisterRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Registers registrarUsuario(Registers registers){
        //guardar los datos en registro 
        return registerRepository.save(registers);
        
    }

    @Transactional
    public Users confirmarRegistro(String tokenConfrimacion){
        //buscar el registro de usuario por token de confirmacion
        Registers registers=registerRepository.findByTokenConfirmacion(tokenConfrimacion).orElseThrow(()-> new IllegalArgumentException("Token de confirmacion invalido"));

        //crear una nueva entidad usuario utilizando los datos de registro 
        Users users=new Users();
        users.setCorreo(registers.getCorreo());
        users.setNombre(registers.getNombre());
        users.setNombreDeUsuario(registers.getNombreDeUsuario());
        users.setFechaNacimiento(registers.getFechaNacimiento());
        users.setFotoPerfil(registers.getFotoPerfil());
        users.setContraseña(registers.getContraseña());

        //guardar el nuevo usuario en la tabla usuario
        Users savedUsers= usersRepository.save(users);

        return savedUsers;
    }

}
