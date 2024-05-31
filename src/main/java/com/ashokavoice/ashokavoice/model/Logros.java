package com.ashokavoice.ashokavoice.model;

import java.sql.Date;

import org.springframework.data.annotation.Transient;

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
    private Long users;

    @Column(nullable = false)
    private boolean oculto;

    @Transient
    private long likes;
    //constructor predeterminado
    public Logros(){

    }
    //constructor con argumentos
    public Logros(String titulo,Date fecha, String area,String descripcion,String imagen,Long users,boolean oculto,long likes){
        this.titulo=titulo;
        this.area=area;
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.imagen=imagen;
        this.users=users;
        this.oculto=oculto;
        this.likes=likes;
    }

    //Metodos

    public Long getIdLogros(){
        return idLogro;
    }

    public void setIdLogros(Long idLogro){
        this.idLogro=idLogro;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public String getArea(){
        return area;
    }
    public void setArea(String area){
        this.area=area;
    }

    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public String getImagen(){
        return imagen;
    }
    public void setImagen(String imagen){
        this.imagen=imagen;
    }

    public Long getUsers(){
        return users;
    }
    public void setUsers(Long users){
        this.users=users;
    }

    public boolean getOculto(){
        return oculto;
    }
    public void setOculto(boolean oculto){
        this.oculto=oculto;
    }

    public long getLikes(){
        return likes;
    }
    public void setLikes(long likes){
        this.likes=likes;
    }


}
