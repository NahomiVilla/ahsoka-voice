package com.ashokavoice.ashokavoice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Feed;
import com.ashokavoice.ashokavoice.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public List<Feed> getAllFeed(){
        return feedService.getAllFeeds();
    }

    @GetMapping("/{id}")
    public Optional<Feed> getFeedById(@PathVariable Long id){
        return feedService.getFeedById(id);
    }

    @PostMapping
    public Feed createFeed(@RequestBody Feed feed){
        return feedService.saveFeed(feed);
    }
}
