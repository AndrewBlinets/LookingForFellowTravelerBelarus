package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class Token {

    private long id;
    private long idUser;
    private String token;

    public Token() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

