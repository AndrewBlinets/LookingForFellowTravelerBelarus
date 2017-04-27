package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class User {

    private int id;
    private String name;
    private String surName;
    private String foto;

    public User() {
    }

    public User(int id, String name, String surName, String foto, String token) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.foto = foto;
    }

    public User(String name, String surName, String foto, String token) {
        this.name = name;
        this.surName = surName;
        this.foto = foto;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
