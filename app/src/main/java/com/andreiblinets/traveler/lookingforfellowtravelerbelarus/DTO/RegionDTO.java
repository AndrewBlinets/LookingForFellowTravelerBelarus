package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO;


public class RegionDTO {

    private long id;
    private long idCountry;
    private String name;

    public RegionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(long idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
