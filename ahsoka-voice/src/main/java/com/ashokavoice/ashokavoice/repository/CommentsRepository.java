package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Comments;
import java.util.List;


@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByLogros_IdLogros(Long idLogros);
    int countByLogros_IdLogros(Long logrosId);
}