package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Extras;


@Repository
public interface ExtraRepository extends JpaRepository<Extras,Long>{
}
 