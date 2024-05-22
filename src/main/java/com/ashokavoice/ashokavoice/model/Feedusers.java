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
@Table(name = "feeduser",
    uniqueConstraints = 
        @UniqueConstraint(columnNames = {"id"}))
public class Feedusers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_logro",nullable=false)
    private Logros logros;

    //constructor predeterminado
    public Feedusers(){

    }

    //constructor con argumentos
    public Feedusers(Users users,Logros logros){
        this.users=users;
        this.logros=logros;
    }

    //METODOS
    
}
