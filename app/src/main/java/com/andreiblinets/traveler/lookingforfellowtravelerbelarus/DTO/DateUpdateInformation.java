package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.DTO;

public class DateUpdateInformation {

    private long id;
    private String dataTimeUpdateCity;
    private String dataTimeUpdateRegion;
    private String dataTimeUpdateCountry;

    public DateUpdateInformation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataTimeUpdateCity() {
        return dataTimeUpdateCity;
    }

    public void setDataTimeUpdateCity(String dataTimeUpdateCity) {
        this.dataTimeUpdateCity = dataTimeUpdateCity;
    }

    public String getDataTimeUpdateRegion() {
        return dataTimeUpdateRegion;
    }

    public void setDataTimeUpdateRegion(String dataTimeUpdateRegion) {
        this.dataTimeUpdateRegion = dataTimeUpdateRegion;
    }

    public String getDataTimeUpdateCountry() {
        return dataTimeUpdateCountry;
    }

    public void setDataTimeUpdateCountry(String dataTimeUpdateCountry) {
        this.dataTimeUpdateCountry = dataTimeUpdateCountry;
    }
}
