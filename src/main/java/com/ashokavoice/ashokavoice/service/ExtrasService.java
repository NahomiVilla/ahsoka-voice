package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class ExtrasService {

    private static final Logger logger = Logger.getLogger(ExtrasService.class.getName());

    @Autowired 
    private LogrosRepository logrosRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Logros> obtenerLogrosPorUsuario(Long idUsuario) {
        logger.info("obteniendo logros de usuario...");
        Users users=usersRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return logrosRepository.findByUsers(users);
    }
    

}
 