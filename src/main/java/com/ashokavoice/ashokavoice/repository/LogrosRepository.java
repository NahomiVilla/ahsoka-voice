package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Logros;

@Repository
public interface LogrosRepository extends JpaRepository<Logros, Long> {

}
