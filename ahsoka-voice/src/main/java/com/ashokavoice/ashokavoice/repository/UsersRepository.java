package com.ashokavoice.ashokavoice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByNombreUsuario(String nombreUsuario);
}
