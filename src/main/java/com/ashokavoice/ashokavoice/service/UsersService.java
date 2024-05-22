package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
 
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Optional<Users> getUserId(Long id){
        return usersRepository.findById(id);
    }

    public Users saveUser(Users user){
        return usersRepository.save(user);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }
}
