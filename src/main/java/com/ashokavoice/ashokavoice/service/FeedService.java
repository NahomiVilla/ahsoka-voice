package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Feed;
import com.ashokavoice.ashokavoice.repository.FeedRepository;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    public List<Feed> getAllFeeds(){ 
        return feedRepository.findAll();
    }

    public Optional<Feed> getFeedById(Long id){
        return feedRepository.findById(id);
    }

    public Feed saveFeed(Feed feed){
        return feedRepository.save(feed);
    }

    public void deleteFeed(Long id){
        feedRepository.deleteById(id);
    }
}
