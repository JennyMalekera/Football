package com.example.Football.Model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "DIVISION")
@SQLDelete(sql = "UPDATE TEAM SET STATUS = 'F' WHERE id=?")
@Where(clause = "STATUS = 'T'")

public class DivisionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIVISIONID", insertable = false, nullable = false,unique = true,updatable = false)
    private int id;


    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private char status;

    public DivisionModel(int id, String name,  char status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public DivisionModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}

