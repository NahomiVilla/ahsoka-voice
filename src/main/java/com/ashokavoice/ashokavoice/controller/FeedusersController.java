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

import com.ashokavoice.ashokavoice.service.FeedusersService;
import com.ashokavoice.ashokavoice.model.Feedusers;



@RestController
@RequestMapping("/feed-user")
public class FeedusersController {

    @Autowired
    private FeedusersService feedusersService;

    @GetMapping
    private List<Feedusers> getAllFeedusers(){
        return feedusersService.getAllFeedusers();
    }

    @GetMapping("/{id}")
    public Optional<Feedusers> getFeedusersById(@PathVariable Long id){
        return feedusersService.getFeedusersById(id);
    }

    @PostMapping
    public Feedusers createFeedusers(@RequestBody Feedusers feedusers){
        return feedusersService.saveFeedusers(feedusers);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedusers(@PathVariable Long id){
        feedusersService.deleteFeedusers(id);
    }
    

    
}
 