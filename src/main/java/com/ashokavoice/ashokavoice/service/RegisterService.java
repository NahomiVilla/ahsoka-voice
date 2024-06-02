package com.ashokavoice.ashokavoice.service;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private EmailService emailService;
    private Map<Long,Integer> tokens=new HashMap<>();//almacena temporalmente los tokens

    @Transactional
    public Registers registrarUsuario(Registers registers){
        //verificar edad de usuario
        if (calcularEdad(registers.getFechaNacimiento())<18){
            throw new IllegalArgumentException("El usuario debe ser mayor a 18 años");
        } 
        //guardad los datos del registro
        Registers savedRegisters=registerRepository.save(registers);
        //Generar token de confirmacion 
        Integer token=generarToken();
        //guardad el token junto con el id del registro temporalmente
        tokens.put(savedRegisters.getIdRegistro(), token);
        //enviar el correo electronico de confirmacion
        emailService.enviarCorreoConfirmacion(registers.getCorreo(), token);
        return savedRegisters;
        
    }

    @Transactional
    public Users confirmarRegistro(Integer tokenConfrimacion){
        //buscar el registro de usuario por token de confirmacion
        Registers registers=registerRepository.findByTokenConfirmacion(tokenConfrimacion).orElseThrow(()-> new IllegalArgumentException("Token de confirmacion invalido"));

        //Verficar que el token coincida
        Integer storedToken=tokens.get(registers.getIdRegistro());
        if(storedToken==null||!storedToken.equals(tokenConfrimacion)){
            throw new IllegalArgumentException("Token de confirmacion invalido");
        }

        //crear una nueva entidad usuario utilizando los datos de registro 
        Users users=new Users();
        users.setCorreo(registers.getCorreo());
        users.setNombre(registers.getNombre());
        users.setNombreUsuario(registers.getNombreUsuario());
        users.setFechaNacimiento(registers.getFechaNacimiento());
        users.setFotoPerfil(registers.getFotoPerfil());
        users.setContraseña(registers.getContraseña());

        //guardar el nuevo usuario en la tabla usuario
        Users savedUsers= usersRepository.save(users);

        //eliminar el token despues de la confirmacion
        tokens.remove(registers.getIdRegistro());

        return savedUsers;
    }

    private Integer generarToken(){
        SecureRandom random=new SecureRandom();
        return random.nextInt(1000000);
    }

    private int calcularEdad(Date fechaNacimiento){
        LocalDate localDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(localDate, LocalDate.now()).getYears();
    }

}
