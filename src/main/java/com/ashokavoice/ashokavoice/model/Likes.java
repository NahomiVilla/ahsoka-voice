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
        @UniqueConstraint(columnNames = {"id_like"})
)

public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id_like;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Long users;

    @ManyToOne
    @JoinColumn(name ="id_logro",nullable=false)
    private Long logros;

    //numero de likes??

    //constructor predeterminado
    public Likes(){

    }

    //constructor con argumentos
    public Likes(Long users,Long logros){
        this.users=users;
        this.logros=logros;
    }

    //METODOS
    public Long getIdLike(){
        return id_like;
    }
    public void setIdLike(Long id_like){
        this.id_like=id_like;
    } 

    public Long getUsers(){
        return users;
    }
    public void setUsers(Long users){
        this.users=users;
    }

    public Long getLogros(){
        return logros;
    }
    public void setLogros(Long logros){
        this.logros=logros;
    }
}
