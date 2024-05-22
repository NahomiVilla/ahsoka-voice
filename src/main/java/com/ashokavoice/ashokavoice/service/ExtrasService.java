package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Extras;
import com.ashokavoice.ashokavoice.repository.ExtraRepository;

@Service
public class ExtrasService {

    @Autowired
    private ExtraRepository extraRepository;

    public List<Extras> getAllExtras(){
        return extraRepository.findAll();
    }

    public Optional<Extras> getExtrasById(Long id){
        return extraRepository.findById(id);
    }

    public Extras saveExtras(Extras extra){
        return extraRepository.save(extra);
    }

    public void deleteExtras(Long id){
        extraRepository.deleteById(id);
    }
}
