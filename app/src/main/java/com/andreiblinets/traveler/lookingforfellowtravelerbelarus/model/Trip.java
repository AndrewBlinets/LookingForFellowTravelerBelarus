package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

import java.io.Serializable;

public class Trip implements Serializable {

    private long id;
    private long idCityOfDeparture;
    private long idCityOfArrived;
    private String dataOfDeparture;
    private String timeOfDeparture;
    private int compensation;
    private int transportId;
    private int countFree;
    private String information;
    private int departureDeviation;
    private boolean autoAdd;

    public Trip() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCityOfDeparture() {
        return idCityOfDeparture;
    }

    public void setIdCityOfDeparture(long idCityOfDeparture) {
        this.idCityOfDeparture = idCityOfDeparture;
    }

    public long getIdCityOfArrived() {
        return idCityOfArrived;
    }

    public void setIdCityOfArrived(long idCityOfArrived) {
        this.idCityOfArrived = idCityOfArrived;
    }

    public String getDataOfDeparture() {
        return dataOfDeparture;
    }

    public void setDataOfDeparture(String dataOfDeparture) {
        this.dataOfDeparture = dataOfDeparture;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public int getCountFree() {
        return countFree;
    }

    public void setCountFree(int countFree) {
        this.countFree = countFree;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getDepartureDeviation() {
        return departureDeviation;
    }

    public void setDepartureDeviation(int departureDeviation) {
        this.departureDeviation = departureDeviation;
    }

    public boolean isAutoAdd() {
        return autoAdd;
    }

    public void setAutoAdd(boolean autoAdd) {
        this.autoAdd = autoAdd;
    }
}
