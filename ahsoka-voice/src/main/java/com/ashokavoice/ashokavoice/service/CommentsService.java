package com.ashokavoice.ashokavoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.CommentsRepository;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Transactional
    public Comments crearComments(Logros logros,Users users,String comentario){
        Comments comment=new Comments();
        comment.setLogros(logros);
        comment.setUsers(users);
        comment.setComentario(comentario);
        return commentsRepository.save(comment);
    }

    @Transactional
    public Comments editarComentario(Long idComentario,Users users,String nuevoComentario) throws IllegalAccessException{
        Comments comment=commentsRepository.findById(idComentario).orElseThrow(()->new IllegalArgumentException("comentario no encontrado"));
        if (!comment.getUsers().equals(users)){
            throw new IllegalAccessException("no tienes permiso para editar ese comentario");//verificamos si quien quiere editar el comentairio es el mismo quien lo creó
        }
        comment.setComentario(nuevoComentario);
        return commentsRepository.save(comment);
    }

    @Transactional
    public void eliminarComentario(Long idComentario,Users users) throws IllegalAccessException{
        Comments comment=commentsRepository.findById(idComentario).orElseThrow(()->new IllegalArgumentException("comentario no encontrado"));
        if (!comment.getUsers().equals(users)){
            throw new IllegalAccessException("no tienes permiso para eliminar este comentario");
        }
        commentsRepository.delete(comment);
    }

    public List<String> obtenerComentarioPorLogro(Long idLogro) {
        List<Comments>comentarios=commentsRepository.findByLogros_IdLogros(idLogro);
        return comentarios.stream().map(Comments::getComentario).collect(Collectors.toList());
    }
}