package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Country {

    private long id;
    private String name;
    private String kodCurrency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKodCurrency() {
        return kodCurrency;
    }

    public void setKodCurrency(String kodCurrency) {
        this.kodCurrency = kodCurrency;
    }
}
