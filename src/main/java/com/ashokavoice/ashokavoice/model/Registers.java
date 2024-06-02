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
@Table(name = "registro",
    uniqueConstraints = 
    @UniqueConstraint(columnNames = {"id_registro"}))
public class Registers {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idRegistro;

    @Column(nullable =false)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false,unique = true)
    private String nombreUsuario;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(nullable = false)
    private String ipRegistro;

    @Column(nullable = true)
    private String fotoPerfil;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private Integer tokenConfirmacion;
    //CONSTRUCTOR PREDETERMINADO
    public Registers(){

    }
    //constructor con argumentos
    public Registers(String correo,String nombre,String nombreUsuario, Date fechaNacimiento, String fotoPerfil, String contrasena, Integer edad, Date fechaRegistro, String ipRegistro,Integer tokenConfirmacion){
        this.correo=correo;
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.fechaNacimiento=fechaNacimiento;
        this.fotoPerfil=fotoPerfil;
        this.nombreUsuario=nombreUsuario;
        this.fechaRegistro=fechaRegistro;
        this.ipRegistro=ipRegistro;
        this.edad=edad;
        this.tokenConfirmacion=tokenConfirmacion;
    }
    //METODOS

    public Long getIdRegistro(){
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro){
        this.idRegistro=idRegistro;
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

    public Date getFechaRegistro(){
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro){
        this.fechaRegistro=fechaRegistro;
    }

    public String getIpRegistro(){
        return ipRegistro;
    }

    public void setIpRegistro(String ipRegistro){
        this.ipRegistro=ipRegistro;
    }

    public Integer getEdad(){
        return edad;
    }
    public void setEdad(Integer edad){
        this.edad=edad;
    }

    public Integer getTokenConfirmacion(){
        return tokenConfirmacion;
    }
    public void setTokenConfirmacion(Integer tokenConfirmacion){
        this.tokenConfirmacion=tokenConfirmacion;
    }
}