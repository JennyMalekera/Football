package com.example.Football.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PLAYER")
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYERID")
    private int id;

    @NotNull(message = "Firstname  name is required")
    @Column(name = "FIRSTNAME")
    private String firstname;


    @Column(name = "LASTNAME")
    private String lastname;


    @Column(name = "AGE")
    private int age;


    @Column(name = "EMAIL")
    private String email;


    @Column(name = "STATUS")
    private char status;


    @Column(name = "TEAMID")
    private int teamId;

    public PlayerModel(int id, String firstname, String lastname, int age, String email, char status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.status = status;
    }

    public PlayerModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}



