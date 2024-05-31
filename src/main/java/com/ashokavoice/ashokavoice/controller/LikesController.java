package com.ashokavoice.ashokavoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired//se conecta con el archivo service
    private LikesService likesService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarLike(@RequestParam Long idLogro,@RequestParam Long idUsuario){
        try{
            likesService.agregarLike(idLogro, idUsuario);
            return ResponseEntity.ok("like agregado exitosamente");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarLike(@RequestParam Long idLogro,@RequestParam Long idUsuario){
        try{
            likesService.eliminarLike(idLogro, idUsuario);
            return ResponseEntity.ok("like eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/visualizar")
    public ResponseEntity<?> visualizarLikes(@RequestParam Long idLogro) {
        try {
            int cantidadLikes = likesService.obtenerCantidadLikes(idLogro);
            return ResponseEntity.ok(cantidadLikes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
