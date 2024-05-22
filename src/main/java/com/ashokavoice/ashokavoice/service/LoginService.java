package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logins;
import com.ashokavoice.ashokavoice.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public List<Logins> getAllLogins() {
        return loginRepository.findAll();
    }

    public Optional<Logins> getLoginById(Long id) {
        return loginRepository.findById(id);
    }

    public Logins saveLogin(Logins login) {
        return loginRepository.save(login);
    }

    public void deleteLogin(Long id) {
        loginRepository.deleteById(id);
    }
}
