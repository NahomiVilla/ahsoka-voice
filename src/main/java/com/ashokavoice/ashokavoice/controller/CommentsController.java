package com.ashokavoice.ashokavoice.controller;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
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
import com.ashokavoice.ashokavoice.repository.LogrosRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;
import com.ashokavoice.ashokavoice.service.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private LogrosRepository logrosRepository;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<?> crearComentario(@RequestParam Long logrosId,@RequestParam Long userId,@RequestBody String comentario){
        try{
            Logros logros=logrosRepository.findById(logrosId).orElseThrow(() -> new IllegalArgumentException("Logro no encontrado"));
            Users users=usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            
            Comments comment=commentsService.crearComments(logros, users, comentario);
            //Map<String, Object> response = new HashMap<>();
            //response.put("logro", logros);
            //response.put("comentario", comment.getComentario());
            return ResponseEntity.ok(comment);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id_comentario}")
    public ResponseEntity<?> editarComentario(@PathVariable Long id_comentario,@RequestParam Long userId,@RequestParam Long logrosId,@RequestBody String nuevoComentario){
        try{
            //Logros logros=logrosRepository.findById(logrosId).orElseThrow(() -> new IllegalArgumentException("Logro no encontrado"));
            Users users=usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            Comments comment=commentsService.editarComentario(id_comentario, users, nuevoComentario);
            //Map<String, Object> response = new HashMap<>();
            //response.put("logro", logros);
            //response.put("comentario", comment.getComentario());
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_comentario}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id_comentario,@RequestParam Long userId){
        try{
            Users users=usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            commentsService.eliminarComentario(id_comentario, users);
            return ResponseEntity.ok("comentario eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //@GetMapping
    public ResponseEntity<?> obtenerComentarioPorLogro(@PathVariable Long idLogro) {
        try {
            List<String> comentarios = commentsService.obtenerComentarioPorLogro(idLogro);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}