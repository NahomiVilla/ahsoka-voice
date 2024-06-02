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
@Table(name ="comentarios",
    uniqueConstraints = 
        @UniqueConstraint(columnNames = {"id_comentario"})
)

public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id_comment;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_logro",nullable=false)
    private Logros logros;

    @Column(name = "comentario")
    private String comentario;

    //constructor predeterminado
    public Comments(){

    }

    //constructor con argumentos
    public Comments(Users users,Logros logros,String comentario){
        this.users=users;
        this.logros=logros;
        this.comentario=comentario;
    }

    //METODOS
    public Long getIdComentario(){
        return id_comment;
    }
    public void setIdComentario(Long id_comment){
        this.id_comment=id_comment;
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

    public String getComentario(){
        return comentario;
    }
    public void setComentario(String comentario){
        this.comentario=comentario;
    }

    public Long getIdLogro() {
        return logros != null ? logros.getIdLogros() : null;
    }
}