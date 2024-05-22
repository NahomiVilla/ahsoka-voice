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
@Table(name = "login",uniqueConstraints = 
@UniqueConstraint(columnNames = {"id_login"}))
public class Logins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_login")
    private Long id_login;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    @Column(nullable = false)
    private String nombre_usuario;

    @Column(nullable = false)
    private String password;
    //constructores
    public Logins(){

    }
    public Logins(Users users,String nombre_usuario,String password){
        this.users=users;
        this.password=password;
        this.nombre_usuario=nombre_usuario;

    }

    //metodos
}
