package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO;

public class CityDTO {

    private long id;
    private long idRegion;
    private String name;

    public CityDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
