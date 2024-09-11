package com.ashokavoice.ashokavoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;
import com.ashokavoice.ashokavoice.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired//se conecta con el archivo service
    private LikesService likesService;
    @Autowired
    private LogrosRepository logrosRepository;
    @Autowired 
    private UsersRepository usersRepository;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarLike(@RequestParam Long logrosId,@RequestParam Long userId){
        try{
            Logros logros=logrosRepository.findById(logrosId).orElseThrow(() -> new IllegalArgumentException("Logro no encontrado"));
            Users users=usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            likesService.agregarLike(logros, users);
            return ResponseEntity.ok("like agregado exitosamente");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarLike(@RequestParam Long logrosId,@RequestParam Long userId){
        try{
            Logros logros=logrosRepository.findById(logrosId).orElseThrow(() -> new IllegalArgumentException("Logro no encontrado"));
            Users users=usersRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            
            likesService.eliminarLike(logros, users);
            return ResponseEntity.ok("like eliminado con exito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //@GetMapping("/visualizar")
    //public ResponseEntity<?> visualizarLikes(@RequestParam Long logrosId) {
      //  try {
        //    int cantidadLikes = likesService.obtenerCantidadLikes(logrosId);
          //  return ResponseEntity.ok(cantidadLikes);
        //} catch (Exception e) {
          //  return ResponseEntity.badRequest().body(e.getMessage());
        //}
    //}
    
}