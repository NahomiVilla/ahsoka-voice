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
import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.service.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearComentario(@RequestParam Logros logros,@RequestParam Users users,@RequestBody String comentario){
        try{
            Comments comment=commentsService.crearComments(logros, users, comentario);
            return ResponseEntity.ok(comment);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id_comment}")
    public ResponseEntity<?> editarComentario(@PathVariable Long idComentario,@RequestParam Users users,@RequestBody String nuevoComentario){
        try{
            Comments comment=commentsService.editarComentario(idComentario, users, nuevoComentario);
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_comment}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long idComentario,@RequestParam Users users){
        try{
            commentsService.eliminarComentario(idComentario, users);
            return ResponseEntity.ok("comentario eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logro/{id_logro}")
    public ResponseEntity<?> obtenerComentarioPorLogro(@PathVariable Long idLogro) {
        try {
            List<Comments> comentarios = commentsService.obtenerComentarioPorLogro(idLogro);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}