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

import com.ashokavoice.ashokavoice.model.Extras;
import com.ashokavoice.ashokavoice.service.ExtrasService;

@RestController
@RequestMapping("/extra")
public class ExtrasController {

    @Autowired
    private ExtrasService extrasService;

    @GetMapping
    public List<Extras> getAllExtras(){
        return extrasService.getAllExtras();
    }

    @GetMapping("/{id}")
    public Optional<Extras> getExtrasById(@PathVariable Long id) {
        return extrasService.getExtrasById(id);
    }

    @PostMapping
    public Extras creatExtras(@RequestBody Extras extras){
        return extrasService.saveExtras(extras);
    }

    @DeleteMapping("/{id}")
    public void deleteExtras(@PathVariable Long id){
        extrasService.deleteExtras(id);
    }
}
