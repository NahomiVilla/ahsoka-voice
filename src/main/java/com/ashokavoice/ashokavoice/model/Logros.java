package com.ashokavoice.ashokavoice.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="logros",uniqueConstraints = 
    @UniqueConstraint(columnNames = {"idLogro","titulo"})
    )
public class Logros {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idLogro;

    @Column(nullable = false,unique = true)
    private String titulo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable=false)
    private Users users;

    //constructor predeterminado
    public Logros(){

    }
    //constructor con argumentos
    public Logros(String titulo,Date fecha, String area,String descripcion,String imagen,Users users){
        this.titulo=titulo;
        this.area=area;
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.imagen=imagen;
        this.users=users;
    }

    //Metodos
}
