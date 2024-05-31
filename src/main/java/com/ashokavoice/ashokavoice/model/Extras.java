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
@Table(name = "extra",uniqueConstraints = 
@UniqueConstraint(columnNames = {"id_extra"}))


public class Extras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extra")
    private Long id_extra;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Long users;


    public Extras(){

    }
    public Extras(Long users){
        this.users=users;

    }

    //METODOS
    public Long getIdExtra(){
        return id_extra;
    }
    public void setIdExtra(Long id_extra){
        this.id_extra=id_extra;
    }

    public Long getUsers(){
        return users;
    }
    public void setUsers(Long users){
        this.users=users;
    }
}
