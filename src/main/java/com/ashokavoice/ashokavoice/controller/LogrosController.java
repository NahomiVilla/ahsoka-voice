package com.ashokavoice.ashokavoice.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
import com.ashokavoice.ashokavoice.repository.UsersRepository;
import com.ashokavoice.ashokavoice.service.CommentsService;
import com.ashokavoice.ashokavoice.service.LikesService;
import com.ashokavoice.ashokavoice.service.LogrosService;

@RestController
@RequestMapping("/logros")
public class LogrosController {
    @Autowired
    private LogrosService logrosService;
    @Autowired
    private LikesService likesService;

    @Autowired
    private CommentsService comentariosService;

    @Autowired
    private UsersRepository usersRepository;

    //crear logro
    @PostMapping
    public ResponseEntity<Logros> crearLogro(@RequestBody Logros logros,@RequestParam Long userId){
        Logros nuevoLogros=logrosService.crearLogros(logros,userId);
        return ResponseEntity.ok(nuevoLogros);
    }
    //lista.all
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Map<String, Object>>> listarTodosMisLogros(@PathVariable Long userId){
        Optional<Users> optionalUser = usersRepository.findById(userId); // Asumiendo que tienes un UsersRepository

        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el usuario no existe
        }

        Users user = optionalUser.get();
        List<Logros> logros = logrosService.listarTodosMisLogros(user);
        List<Map<String, Object>> logrosConDetalles = new ArrayList<>();

        for (Logros logro : logros) {
            int cantidadLikes = likesService.obtenerCantidadLikes(logro.getIdLogros());
            List<String> comentarios = comentariosService.obtenerComentarioPorLogro(logro.getIdLogros());

            Map<String, Object> logroConDetalles = new HashMap<>();
            logroConDetalles.put("logro", logro);
            logroConDetalles.put("likes", cantidadLikes);
            logroConDetalles.put("comentarios", comentarios);

            logrosConDetalles.add(logroConDetalles);
        }
        return ResponseEntity.ok(logrosConDetalles);
    }
    //lista.feed
    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<Map<String, Object>>> listarLogrosFeed(@PathVariable Long userId){
        Optional<Users> optionalUser = usersRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Users user = optionalUser.get();
        List<Logros> logroFeed=logrosService.listarLogrosFeed(user);
        List<Map<String, Object>> logrosFeedConDetalles = new ArrayList<>();

        for (Logros logro : logroFeed) {
             // Solo agregar logros que no están ocultos
            if (!logro.getOculto()) {
                int cantidadLikes = likesService.obtenerCantidadLikes(logro.getIdLogros());
                List<String> comentarios = comentariosService.obtenerComentarioPorLogro(logro.getIdLogros());

                Map<String, Object> logroConDetalles = new HashMap<>();
                logroConDetalles.put("logro", logro);
                logroConDetalles.put("likes", cantidadLikes);
                logroConDetalles.put("comentarios", comentarios);

                logrosFeedConDetalles.add(logroConDetalles);
        }}
        return ResponseEntity.ok(logrosFeedConDetalles);
    }
    //editar logro
    @PutMapping("/{id_logro}")
    public ResponseEntity<Logros> editarLogro(@PathVariable Long id_logro,@RequestBody Logros logros){
        Optional<Logros> logroEditado=logrosService.editarLogro(id_logro, logros);
        return logroEditado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    //eliminar logro
    @DeleteMapping("/{id_logro}")
    public ResponseEntity<Void> eliminarLogro(@PathVariable Long id_logro) {
        if (logrosService.eliminarLogro(id_logro)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    //ocultar logro
    @PutMapping("/ocultar/{id_logro}")
    public ResponseEntity<Logros> ocultarLogro(@PathVariable Long id_logro) {
        Optional<Logros> logroOcultado = logrosService.ocultarLogro(id_logro);
        return logroOcultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}