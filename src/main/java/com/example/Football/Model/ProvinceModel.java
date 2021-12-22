package com.example.Football.Model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PROVINCE")

@SQLDelete(sql = "UPDATE PROVINCE SET STATUS = 'F' WHERE PROVINCEID=?")
@Where(clause = "STATUS = 'T'")

public class ProvinceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVINCEID", insertable = false, nullable = false,unique = true,updatable = false)
    //@Column(name = "PROVINCEID", insertable = false)
    private int id;


    @NotNull(message = "Province name is required")
    @Column(name = "PNAME")
    private String pname;

    @NotNull(message = "Status  is required")
    @Column(name = "STATUS")
    private char status;

    public ProvinceModel(int id, String pname, char status) {
        this.id = id;
        this.pname = pname;
        this.status = status;
    }

    public ProvinceModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = 'T';
    }
}

