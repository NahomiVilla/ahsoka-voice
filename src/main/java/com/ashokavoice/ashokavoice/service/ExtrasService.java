package com.ashokavoice.ashokavoice.service;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;

@Service
public class ExtrasService {

    //@Autowired
    //private ExtraRepository extraRepository;

    @Autowired 
    private LogrosRepository logrosRepository;

    public void writeLogrosToCsv(PrintWriter writer , Users users){
        List<Logros> logros=logrosRepository.findByUsers(users);
        writer.write("ID,Titulo,Fecha,Área,Descripción\n");
        for (Logros logro:logros){
            writer.write(String.format("%d","%s","%s","%s","%s\n",
            logro.getIdLogros(),
            logro.getTitulo(),
            logro.getFecha().toString(),
            logro.getArea(),
            logro.getDescripcion()));
        }
    }

}
 