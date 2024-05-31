package com.ashokavoice.ashokavoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokavoice.ashokavoice.model.Likes;
import java.util.List;
import java.util.Optional;


public interface LikesRepository extends JpaRepository <Likes,Long>{
    List<Likes> findByIdLike(Long idLike);
    boolean existsByIdLogroAndIdUsuario(Long idLogro, Long idUsuario);
    Optional<Likes> findByIdLogroAndIdUsuario(Long idLogro, Long idUsuario);
    int countByIdLogro(Long idLogro);
}
 