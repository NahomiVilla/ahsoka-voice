package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Likes;
import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository <Likes,Long>{
    List<Likes> findByIdLikes(Long idLikes);
    boolean existsByLogrosAndUsers(Logros logros, Users users);
    Optional<Likes> findByLogrosAndUsers(Logros logros, Users users);
    int countByLogros_IdLogros(Long logrosId);
}
 