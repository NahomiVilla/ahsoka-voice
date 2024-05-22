package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Feedusers;

@Repository
public interface FeedusersRepository extends JpaRepository<Feedusers,Long>{

}