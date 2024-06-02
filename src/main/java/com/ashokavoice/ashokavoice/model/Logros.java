package com.ashokavoice.ashokavoice.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Long idLogros;

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

    @Column(nullable = false)
    private boolean oculto;

    @Transient
    private long likes;

    @OneToMany(mappedBy = "logros")
    private List<Comments> comentarios;
    @OneToMany(mappedBy = "logros")
    private List<Likes> like;

    //constructor predeterminado
    public Logros(){

    }
    //constructor con argumentos
    public Logros(String titulo,List<Likes> like,Date fecha, String area,String descripcion,List<Comments> comentarios,String imagen,Users users,boolean oculto,long likes){
        this.titulo=titulo;
        this.area=area;
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.imagen=imagen;
        this.users=users;
        this.oculto=oculto;
        this.likes=likes;
        this.comentarios=comentarios;
        this.like=like;
    }

    //Metodos

    public Long getIdLogros(){
        return idLogros;
    }

    public void setIdLogros(Long idLogros){
        this.idLogros=idLogros;
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

    public Users getUsers(){
        return users;
    }
    public void setUsers(Users users){
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
    public List<Comments> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comments> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Likes> getLike(){
        return like;
    }
    public void setLike(List<Likes> like){
        this.like=like;
    }
}