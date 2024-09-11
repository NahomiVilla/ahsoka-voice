package com.ashokavoice.ashokavoice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;


@Repository
public interface LogrosRepository extends JpaRepository<Logros, Long> {
    List<Logros> findByUsers(Users users);
    
}