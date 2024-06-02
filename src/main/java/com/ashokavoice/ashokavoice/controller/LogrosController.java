package com.ashokavoice.ashokavoice.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ashokavoice.ashokavoice.model.Comments;
import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
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

    //crear logro
    @PostMapping
    public ResponseEntity<Logros> crearLogro(@RequestBody Logros logros){
        Logros nuevoLogros=logrosService.crearLogros(logros);
        return ResponseEntity.ok(nuevoLogros);
    }
    //lista.all
    @GetMapping("/all/{users}")
    public ResponseEntity<List<Map<String, Object>>> listarTodosMisLogros(@PathVariable Users users){
        List<Logros> logros=logrosService.listarTodosMisLogros(users);
        List<Map<String, Object>> logrosConDetalles = new ArrayList<>();

        for (Logros logro : logros) {
            int cantidadLikes = likesService.obtenerCantidadLikes(logro.getIdLogros());
            List<Comments> comentarios = comentariosService.obtenerComentarioPorLogro(logro.getIdLogros());

            Map<String, Object> logroConDetalles = new HashMap<>();
            logroConDetalles.put("logro", logro);
            logroConDetalles.put("likes", cantidadLikes);
            logroConDetalles.put("comentarios", comentarios);

            logrosConDetalles.add(logroConDetalles);
        }
        return ResponseEntity.ok(logrosConDetalles);
    }
    //lista.feed
    @GetMapping("/feed/{users}")
    public ResponseEntity<List<Map<String, Object>>> listarLogrosFeed(@PathVariable Users users){
        List<Logros> logroFeed=logrosService.listarLogrosFeed(users);
        List<Map<String, Object>> logrosFeedConDetalles = new ArrayList<>();

        for (Logros logro : logroFeed) {
            int cantidadLikes = likesService.obtenerCantidadLikes(logro.getIdLogros());
            List<Comments> comentarios = comentariosService.obtenerComentarioPorLogro(logro.getIdLogros());

            Map<String, Object> logroConDetalles = new HashMap<>();
            logroConDetalles.put("logro", logro);
            logroConDetalles.put("likes", cantidadLikes);
            logroConDetalles.put("comentarios", comentarios);

            logrosFeedConDetalles.add(logroConDetalles);
        }
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
    public ResponseEntity<Logros> ocultarLogro(@PathVariable Long idLogro) {
        Optional<Logros> logroOcultado = logrosService.ocultarLogro(idLogro);
        return logroOcultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}