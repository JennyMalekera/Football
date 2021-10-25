package com.example.Football.Model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "TEAM")
 //@SQLDelete(sql = "UPDATE TEAM SET STATUS = 'F' WHERE id=?")
 //@Where(clause = "STATUS = 'T'")

public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAMID", insertable = false, nullable = false,unique = true,updatable = false)
    private int id;


    @Column(name = "TNAME")
    private String tname;

    @Column(name = "STATUS")
    private char status;

    @Column(name = "PROVINCEID")
    private int ProvinceId;

    @Column(name = "DIVISIONID")
    private int DivisionId;

    public TeamModel(int id, String tname, char status, int provinceId, int divisionId) {
        this.id = id;
        this.tname = tname;
        this.status = status;
        ProvinceId = provinceId;
        DivisionId = divisionId;
    }

    public TeamModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public int getDivisionId() {
        return DivisionId;
    }

    public void setDivisionId(int divisionId) {
        DivisionId = divisionId;
    }
}