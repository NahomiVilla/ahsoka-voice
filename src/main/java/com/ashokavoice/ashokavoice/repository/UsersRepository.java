package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

}
