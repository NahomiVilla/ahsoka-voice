package com.ashokavoice.ashokavoice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.model.Users;
//import com.ashokavoice.ashokavoice.repository.CommentsRepository;
import com.ashokavoice.ashokavoice.repository.LikesRepository;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;
import com.ashokavoice.ashokavoice.repository.UsersRepository;

@Service
public class LogrosService {
    @Autowired
    private LogrosRepository logrosRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private UsersRepository usersRepository;

    //@Autowired
    //private CommentsRepository commentsRepository;
    public Logros crearLogros(Logros logros,Long userId){
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isPresent()) {
            logros.setUsers(userId);
            return logrosRepository.save(logros);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        
    }

    public List<Logros> listarTodosMisLogros(Users users){
        List<Logros> logro = logrosRepository.findByUsers(users);
        for (Logros logros : logro) {
            logros.setLikes(likesRepository.countByLogros_IdLogros(logros.getIdLogros()));
            //logros.setComentarios(commentsRepository.findByLogros_IdLogros(logros.getIdLogros()));
        }
        return logro;
    }

    public List<Logros> listarLogrosFeed(Users users){
        List<Logros> todosLogros=logrosRepository.findByUsers(users);
    
        if (todosLogros.isEmpty()) {
            return new ArrayList<>();
        }
        
        Random random = new Random();
        Logros logroAleatorio = todosLogros.get(random.nextInt(todosLogros.size()));
        logroAleatorio.setLikes(likesRepository.countByLogros_IdLogros(logroAleatorio.getIdLogros()));
        
        // Filtrar logros que no están ocultos
        List<Logros> otrosLogrosAleatorios = logrosRepository.findAll().stream()
            .filter(logros -> !logros.getUsers().equals(users.getIdUsuario()) && !logros.getOculto())
            .collect(Collectors.toList());
    
        // Se añade los logros a la lista feed
        List<Logros> logrosFeed = new ArrayList<>();
        logrosFeed.add(logroAleatorio);
        logrosFeed.addAll(otrosLogrosAleatorios);

        return logrosFeed;
    }

    public Optional<Logros> editarLogro(Long id_logro,Logros logros){
        return logrosRepository.findById(id_logro).map(existingLogro -> {
            existingLogro.setTitulo(logros.getTitulo());
            existingLogro.setFecha(logros.getFecha());
            existingLogro.setArea(logros.getArea());
            existingLogro.setDescripcion(logros.getDescripcion());
            existingLogro.setImagen(logros.getImagen());
            return logrosRepository.save(existingLogro);
        });
    }

    public boolean eliminarLogro(Long id_logro){
        return logrosRepository.findById(id_logro).map(logros-> {
            logrosRepository.delete(logros);
            return true;
        }).orElse(false);
    }

    public Optional <Logros> ocultarLogro(Long id_logro){
        return logrosRepository.findById(id_logro).map(logros->{
            logros.setOculto(true);
            return logrosRepository.save(logros);
        });
    }
}