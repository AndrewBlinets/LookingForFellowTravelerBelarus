package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class Token extends BaseClass {

    private long idUser;
    private String token;

    public Token() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (idUser != token1.idUser) return false;
        return token != null ? token.equals(token1.token) : token1.token == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "idUser=" + idUser +
                ", token='" + token + '\'' +
                '}';
    }
}

