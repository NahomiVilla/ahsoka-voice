package com.ashokavoice.ashokavoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.service.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearComentario(@RequestParam Long idLogro,@RequestParam Long idUsuario,@RequestBody String comentario){
        try{
            Comments comment=commentsService.crearComments(idLogro, idUsuario, comentario);
            return ResponseEntity.ok(comment);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id_comment}")
    public ResponseEntity<?> editarComentario(@PathVariable Long idComentario,@RequestParam Long idUsuario,@RequestBody String nuevoComentario){
        try{
            Comments comment=commentsService.editarComentario(idComentario, idUsuario, nuevoComentario);
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_comment}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long idComentario,@RequestParam Long idUsuario){
        try{
            commentsService.eliminarComentario(idComentario, idUsuario);
            return ResponseEntity.ok("comentario eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logro/{id_logro}")
    public ResponseEntity<?> obtenerComentarioPorLogro(@PathVariable Long idLogro){
        try{
            List<Comments> comment=commentsService.obtenerComentarioPorLogro(idLogro);
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
