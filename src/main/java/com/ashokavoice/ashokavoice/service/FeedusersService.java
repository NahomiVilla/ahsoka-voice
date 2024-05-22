package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.repository.FeedusersRepository;
import com.ashokavoice.ashokavoice.model.Feedusers;
@Service
public class FeedusersService {

    @Autowired
    private FeedusersRepository feedusersRepository;

    public List<Feedusers> getAllFeedusers(){
        return feedusersRepository.findAll();
    }

    public Optional<Feedusers> getFeedusersById(Long id){
        return feedusersRepository.findById(id);
    }

    public Feedusers saveFeedusers(Feedusers feedusers){
        return feedusersRepository.save(feedusers);
    }

    public void deleteFeedusers(Long id){
        feedusersRepository.deleteById(id);
    }


}
