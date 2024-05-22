package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ashokavoice.ashokavoice.model.Logins;

@Repository
public interface LoginRepository extends JpaRepository<Logins,Long> {

}
