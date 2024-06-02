package com.ashokavoice.ashokavoice.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Likes;
import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.LikesRepository;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    //agregar 
    @Transactional
    public void agregarLike(Logros logros,Users users){
        if (likesRepository.existsByLogrosAndUsers(logros,users)){
            throw new IllegalArgumentException("Ya has dado like a este logro");
        }
        Likes like=new Likes(users,logros);
        //like.setLogros(logros);
        //like.setUsers(users);
        likesRepository.save(like);
    }


    //eliminar()
    @Transactional
    public void eliminarLike(Logros logros,Users users){
        Likes like =likesRepository.findByLogrosAndUsers(logros, users).orElseThrow(()->new IllegalArgumentException("no has dado like a este logro"));
        likesRepository.delete(like);
    }

    //visualizar
    public int obtenerCantidadLikes(Long logrosId){
        return likesRepository.countByLogros_IdLogros(logrosId);
    }
}