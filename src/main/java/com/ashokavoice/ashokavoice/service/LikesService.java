package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Likes;
import com.ashokavoice.ashokavoice.repository.LikesRepository;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    public List<Likes> getAllLikes(){
        return likesRepository.findAll();
    }

    public Optional<Likes> getLikesById(Long id){
        return likesRepository.findById(id);
    }

    public Likes savLikes(Likes likes){
        return likesRepository.save(likes);
    }

    public void deleteLikes(Long id){
        likesRepository.deleteById(id);
    }
}
