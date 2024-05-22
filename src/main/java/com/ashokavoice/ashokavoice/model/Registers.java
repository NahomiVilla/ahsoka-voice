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
    //COSTRUCTOS PREDETERMINADO
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

}
