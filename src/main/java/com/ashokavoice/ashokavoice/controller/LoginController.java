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

import com.ashokavoice.ashokavoice.model.Logins;
import com.ashokavoice.ashokavoice.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<Logins> getAllLogins(){
        return loginService.getAllLogins();
    }

    @GetMapping("/{id}")
    public Optional<Logins> getLoginById(@PathVariable Long id) {
        return loginService.getLoginById(id);
    }

    @PostMapping
    public Logins createLogin(@RequestBody Logins login) {
        return loginService.saveLogin(login);
    }

    @DeleteMapping("/{id}")
    public void deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
    }
}
