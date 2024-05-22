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

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.service.CommentsService;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping
    public List<Comments> getAllComments(){
        return commentsService.getAllComments();
    }

    @GetMapping("/{id}")
    public Optional<Comments> getCommentsById(@PathVariable Long id){
        return commentsService.getCommentsById(id);
    }

    @PostMapping
    public Comments createComments(@RequestBody Comments comments){
        return commentsService.saveComments(comments);
    }

    @DeleteMapping("/{id}")
    public void deleteComments(@PathVariable Long id){
        commentsService.deleteComments(id);
    }
}
