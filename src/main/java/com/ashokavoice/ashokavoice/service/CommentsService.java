package com.ashokavoice.ashokavoice.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.repository.CommentsRepository;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Transactional
    public Comments crearComments(Long idLogro,Long idUsuario,String comentario){
        Comments comment=new Comments();
        comment.setLogros(idLogro);
        comment.setUsers(idUsuario);
        comment.setComentario(comentario);
        return commentsRepository.save(comment);
    }

    @Transactional
    public Comments editarComentario(Long idComentario,Long idUsuario,String nuevoComentario) throws IllegalAccessException{
        Comments comment=commentsRepository.findById(idComentario).orElseThrow(()->new IllegalArgumentException("comentario no encontrado"));
        if (!comment.getUsers().equals(idUsuario)){
            throw new IllegalAccessException("no tienes permiso para editar ese comentario");//verificamos si quien quiere editar el comentairio es el mismo quien lo creó
        }
        comment.setComentario(nuevoComentario);
        return commentsRepository.save(comment);
    }

    @Transactional
    public void eliminarComentario(Long idComentario,Long idUsuario) throws IllegalAccessException{
        Comments comment=commentsRepository.findById(idComentario).orElseThrow(()->new IllegalArgumentException("comentario no encontrado"));
        if (!comment.getUsers().equals(idUsuario)){
            throw new IllegalAccessException("no tienes permiso para eliminar este comentario");
        }
        commentsRepository.delete(comment);
    }

    public List<Comments> obtenerComentarioPorLogro(Long idLogro){
        return commentsRepository.findByIdLogros(idLogro);
    }
}
