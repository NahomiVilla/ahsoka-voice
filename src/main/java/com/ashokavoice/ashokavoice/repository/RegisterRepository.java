package com.ashokavoice.ashokavoice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Registers;

@Repository
public interface RegisterRepository  extends JpaRepository<Registers,Long>{

    Optional<Registers> findByTokenConfirmacion(String tokenConfirmacion);
    
}
