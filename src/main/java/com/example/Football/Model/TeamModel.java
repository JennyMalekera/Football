package com.example.Football.Model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TEAM")
  @SQLDelete(sql = "UPDATE TEAM SET STATUS = 'F' WHERE TEAMID=?")
  @Where(clause = "STATUS = 'T'")

public class TeamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAMID", insertable = false, nullable = false,unique = true,updatable = false)
    private int id;

    @NotNull(message = "Team name is required")
    @Column(name = "TNAME")
    private String tname;

    @NotNull(message = "Team status is required")
    @Column(name = "STATUS")
    private char status;

    @NotNull(message = "ProvinceId is required")
    @Column(name = "PROVINCEID")
    private int ProvinceId;

    @NotNull(message = "DivisionId is required")
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
        this.status = 'T';
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