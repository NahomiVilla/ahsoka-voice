package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.repository.CommentsRepository;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> getAllComments(){
        return commentsRepository.findAll();
    }

    public Optional<Comments> getCommentsById(Long id){
        return commentsRepository.findById(id);
    }

    public Comments saveComments(Comments comments){
        return commentsRepository.save(comments);
    }

    public void deleteComments(Long id){
        commentsRepository.deleteById(id);
    }
}
