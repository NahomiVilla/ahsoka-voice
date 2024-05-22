package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;

@Service
public class LogrosService {
    @Autowired
    private LogrosRepository logrosRepository;

    public List<Logros> getAllLogros(){
        return logrosRepository.findAll();
    }

    public Optional<Logros> getLogrosById(Long id){
        return logrosRepository.findById(id);
    }

    public Logros saveLogros(Logros logros){
        return logrosRepository.save(logros);
    }

    public void deleteLogros(Long id){
        logrosRepository.deleteById(id);

    }
}
