package com.ashokavoice.ashokavoice.service;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Registers;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.RegisterRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class RegisterService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);


    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;
    private Map<Long,Integer> tokens=new HashMap<>();//almacena temporalmente los tokens

    @Transactional
    public Registers registrarUsuario(Registers registers){
        logger.debug("Iniciando registro de usuario");
        //verificar edad de usuario
        if (calcularEdad(registers.getFechaNacimiento())<18){
            throw new IllegalArgumentException("El usuario debe ser mayor a 18 años");
        } 
        // Asegúrate de que la contraseña no sea nula antes de continuar
        if (registers.getContrasena() == null || registers.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        logger.debug("Antes de hashear la contraseña");
        // Hashear la contraseña
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        registers.setContrasena(passwordEncoder.encode(registers.getContrasena()));
        logger.debug("Antes de guardar los datos del registro");
        //guardad los datos del registro
        Registers savedRegisters=registerRepository.save(registers);
        // Log después de guardar los datos
        logger.debug("Después de guardar los datos del registro");
        //Generar token de confirmacion 
        Integer token=generarToken();
        registers.setTokenConfirmacion(token);
        logger.debug("Antes de enviar el correo electrónico de confirmación");

        //guardad el token junto con el id del registro temporalmente
        tokens.put(savedRegisters.getIdRegistro(), token);
        //enviar el correo electronico de confirmacion
        emailService.enviarCorreoConfirmacion(registers.getCorreo(), token);
        logger.debug("Correo electrónico de confirmación enviado");
        return savedRegisters;
        
    }

    @Transactional
    public Users confirmarRegistro(Integer tokenConfrimacion){
        logger.debug("Token recibido para confirmación: " + tokenConfrimacion);
        //buscar el registro de usuario por token de confirmacion
        Registers registers=registerRepository.findByTokenConfirmacion(tokenConfrimacion).orElseThrow(()-> new IllegalArgumentException("Token de confirmacion invalido"));
        logger.debug("Token encontrado en la base de datos: " + registers.getTokenConfirmacion());
        

        //crear una nueva entidad usuario utilizando los datos de registro 
        Users users=new Users();
        users.setCorreo(registers.getCorreo());
        users.setNombre(registers.getNombre());
        users.setNombreUsuario(registers.getNombreUsuario());
        users.setFechaNacimiento(registers.getFechaNacimiento());
        users.setFotoPerfil(registers.getFotoPerfil());
        users.setContrasena(registers.getContrasena());

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
        LocalDate localDate = fechaNacimiento.toLocalDate();
        return Period.between(localDate, LocalDate.now()).getYears();
    }

}
