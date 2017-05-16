package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class Trip extends BaseClass {

    private long idCityOfDeparture;
    private long idCityOfArrived;
    private String dataTimeOfDeparture;
    private int compensation;
    private int transportId;
    private int placeFree;
    private String information;
    private int departureDeviation;

    public Trip() {
        super();
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

    public String getDataTimeOfDeparture() {
        return dataTimeOfDeparture;
    }

    public void setDataTimeOfDeparture(String dataTimeOfDeparture) {
        this.dataTimeOfDeparture = dataTimeOfDeparture;
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

    public int getPlaceFree() {
        return placeFree;
    }

    public void setPlaceFree(int placeFree) {
        this.placeFree = placeFree;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        if (idCityOfDeparture != trip.idCityOfDeparture) return false;
        if (idCityOfArrived != trip.idCityOfArrived) return false;
        if (compensation != trip.compensation) return false;
        if (transportId != trip.transportId) return false;
        if (placeFree != trip.placeFree) return false;
        if (departureDeviation != trip.departureDeviation) return false;
        if (dataTimeOfDeparture != null ? !dataTimeOfDeparture.equals(trip.dataTimeOfDeparture) : trip.dataTimeOfDeparture != null)
            return false;
        return information != null ? information.equals(trip.information) : trip.information == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idCityOfDeparture ^ (idCityOfDeparture >>> 32));
        result = 31 * result + (int) (idCityOfArrived ^ (idCityOfArrived >>> 32));
        result = 31 * result + (dataTimeOfDeparture != null ? dataTimeOfDeparture.hashCode() : 0);
        result = 31 * result + compensation;
        result = 31 * result + transportId;
        result = 31 * result + placeFree;
        result = 31 * result + (information != null ? information.hashCode() : 0);
        result = 31 * result + departureDeviation;
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "idCityOfDeparture=" + idCityOfDeparture +
                ", idCityOfArrived=" + idCityOfArrived +
                ", dataTimeOfDeparture='" + dataTimeOfDeparture + '\'' +
                ", compensation=" + compensation +
                ", transportId=" + transportId +
                ", placeFree=" + placeFree +
                ", information='" + information + '\'' +
                ", departureDeviation=" + departureDeviation +
                '}';
    }
}
