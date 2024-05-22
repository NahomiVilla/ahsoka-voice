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
@Table(name = "feed",
    uniqueConstraints = 
        @UniqueConstraint(columnNames = {"id_feed"}))
public class Feed {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name ="id_logro",nullable=false)
    private Logros logros;

    @ManyToOne
    @JoinColumn(name ="id_comentario" )
    private Comments comments;

    @ManyToOne
    @JoinColumn(name = "id_like")
    private Likes likes;
    //constructor predeterminado
    public Feed(){

    }

    //constructor con argumentos
    public Feed(Users users,Logros logros,Likes likes,Comments comments){
        this.users=users;
        this.logros=logros;
        this.likes=likes;
        this.comments=comments;
    }

    //METODOS
    
}
