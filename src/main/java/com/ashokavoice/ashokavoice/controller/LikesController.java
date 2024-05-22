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

import com.ashokavoice.ashokavoice.model.Likes;
import com.ashokavoice.ashokavoice.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired//se conecta con el archivo service
    private LikesService likesService;

    @GetMapping
    public List<Likes> getAllLikes(){
        return likesService.getAllLikes();
    }

    @GetMapping("/{id}")
    public Optional<Likes> getLikesById(@PathVariable Long id){
        return likesService.getLikesById(id);
    } 

    @PostMapping
    public Likes createLikes(@RequestBody Likes likes){
        return likesService.savLikes(likes);
    }

    @DeleteMapping("{id}")
    public void deleteLikes(@PathVariable Long id){
        likesService.deleteLikes(id);
    }
}
