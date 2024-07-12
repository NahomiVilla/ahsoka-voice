package com.ashokavoice.ashokavoice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name ="likes",
    uniqueConstraints = 
        @UniqueConstraint(columnNames = {"idLike"})
)

public class Likes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_likes", nullable = false)
    private Long idLikes;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name ="id_logros",nullable=false)
    private Logros logros;

    //numero de likes??

    //constructor predeterminado
    public Likes(){

    }

    //constructor con argumentos
    public Likes(Users users,Logros logros){
        this.users=users;
        this.logros=logros;
    }

    //METODOS
    public Long getIdLike(){
        return idLikes;
    }
    public void setIdLike(Long idLikes){
        this.idLikes=idLikes;
    } 

    public Users getUsers(){
        return users;
    }
    public void setUsers(Users users){
        this.users=users;
    }

    public Logros getLogros(){
        return logros;
    }
    public void setLogros(Logros logros){
        this.logros=logros;
    }
    public Long getIdLogro() {
        return logros != null ? logros.getIdLogros() : null;
    }
    public Long getIdUsuario() {
        return users != null ? users.getIdUsuario() : null;
    }
}