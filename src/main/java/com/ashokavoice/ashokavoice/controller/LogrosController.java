package com.ashokavoice.ashokavoice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.service.LogrosService;

@RestController
@RequestMapping("/logros")
public class LogrosController {
    @Autowired
    private LogrosService logrosService;

    @GetMapping
    public List<Logros> getAllLogros(){
        return logrosService.getAllLogros();
    }

    @GetMapping("/{id}")
    public Optional<Logros> getLogroById(@PathVariable Long id){
        return logrosService.getLogrosById(id);
    }

    @PostMapping
    public Logros createLogros(@RequestBody Logros logros){
        return logrosService.saveLogros(logros);
    }

    @DeleteMapping("/{id}")
    public void deleteLogros(@PathVariable Long id){
        logrosService.deleteLogros(id);
    }
}
