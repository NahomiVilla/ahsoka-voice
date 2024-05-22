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
    private Users users;


    public Extras(){

    }
    public Extras(Users users){
        this.users=users;

    }
}
