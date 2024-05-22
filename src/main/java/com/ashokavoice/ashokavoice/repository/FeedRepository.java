package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed,Long>{

}
