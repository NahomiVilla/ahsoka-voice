package com.ashokavoice.ashokavoice.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Likes;
import com.ashokavoice.ashokavoice.repository.LikesRepository;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    //agregar 
    @Transactional
    public void agregarLike(Long idLogro,Long idUsuario){
        if (likesRepository.existsByIdLogroAndIdUsuario(idLogro,idUsuario)){
            throw new IllegalArgumentException("Ya has dado like a este logro");

        }
        Likes like=new Likes();
        like.setLogros(idLogro);
        like.setUsers(idUsuario);
        likesRepository.save(like);
    }


    //eliminar()
    @Transactional
    public void eliminarLike(Long idLogro,Long idUsuario){
        Likes like =likesRepository.findByIdLogroAndIdUsuario(idLogro, idUsuario).orElseThrow(()->new IllegalArgumentException("no has dado like a este logro"));
        likesRepository.delete(like);
    }

    //visualizar
    public int obtenerCantidadLikes(Long idLogro){
        return likesRepository.countByIdLogro(idLogro);
    }
}
