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
    public Long getIdLogin(){
        return id_login;
    }
    public void setIdLogin(Long id_login){
        this.id_login=id_login;
    }

    public Users getUsers(){
        return users;
    }
    public void setUsers(Users users){
        this.users=users;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getNombreUsuario(){
        return nombre_usuario;
    }
    public void setNombreUsuario(String nombre_usuario){
        this.nombre_usuario=nombre_usuario;
    }
}
