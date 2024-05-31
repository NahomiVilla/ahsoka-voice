package com.ashokavoice.ashokavoice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokavoice.ashokavoice.model.Logros;
import com.ashokavoice.ashokavoice.repository.LikesRepository;
import com.ashokavoice.ashokavoice.repository.LogrosRepository;

@Service
public class LogrosService {
    @Autowired
    private LogrosRepository logrosRepository;

    @Autowired
    private LikesRepository likesRepository;

    public Logros crearLogros(Logros logros){
        return logrosRepository.save(logros);
    }

    public List<Logros> listarTodosMisLogros(Long users){
        List<Logros> logro = logrosRepository.findByUsers(users);
        for (Logros logros : logro) {
            logros.setLikes(likesRepository.countByIdLogro(logros.getIdLogros()));
        }
        return logro;
    }

    public List<Logros> listarLogrosFeed(Long users){
        List<Logros> todosLogros=logrosRepository.findByUsers(users);
        if (todosLogros.isEmpty()){
            return new ArrayList<>();
        }
        Random random=new Random();
        Logros logroAleatorio=todosLogros.get(random.nextInt(todosLogros.size()));
        logroAleatorio.setLikes(likesRepository.countByIdLogro(logroAleatorio.getIdLogros()));
        //logros aleatorios de de otros usuarios
        List<Logros> otrosLogrosAleatorios=logrosRepository.findAll().stream().filter(logros -> !logros.getUsers().equals(users)).collect(Collectors.toList());

        //se añade los logros a la lista feed
        List<Logros> logrosFeed=new ArrayList<>();
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
