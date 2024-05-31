package com.ashokavoice.ashokavoice.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name ="usuarios",
    uniqueConstraints = 
    @UniqueConstraint(columnNames = {"nombreDeUsuario","idUsuario"})
)

public class Users {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable =false)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false,unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(nullable = true)
    private String fotoPerfil;

    @Column(nullable = false)
    private String contrasena;

    @Column
    private boolean confirmado;

    //COSTRUCTOS PREDETERMINADO
    public Users(){

    }
    //constructor con argumentos
    public Users(String correo,String nombre,String nombreUsuario, Date fechaNacimiento, String fotoPerfil, String contrasena,boolean confirmado){
        this.correo=correo;
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.fechaNacimiento=fechaNacimiento;
        this.fotoPerfil=fotoPerfil;
        this.nombreUsuario=nombreUsuario;
        this.confirmado=confirmado;
    }
    //METODOS getter y setter

    public Long getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario){
        this.idUsuario=idUsuario;
    }

    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getContraseña(){
        return contrasena;
    }

    public void setContraseña(String contrasena){
        this.contrasena=contrasena;
    }

    public Date getFechaNacimiento(){
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimiento=fechaNacimiento;
    }

    public String getFotoPerfil(){
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil){
        this.fotoPerfil=fotoPerfil;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario=nombreUsuario;
    }

    public boolean getConfirmado(){
        return confirmado;
    }
    public void setConfirmado(boolean confirmado){
        this.confirmado=confirmado;
    }

}
