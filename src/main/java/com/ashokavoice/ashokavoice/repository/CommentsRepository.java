package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokavoice.ashokavoice.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

}
