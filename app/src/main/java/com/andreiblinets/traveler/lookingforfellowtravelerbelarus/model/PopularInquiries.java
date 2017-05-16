package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class PopularInquiries extends BaseClass {

    private int idCityOfDeparture;
    private int idCityOfArrived;
    private int count;

    public PopularInquiries() {
        super();
    }

    public int getIdCityOfDeparture() {
        return idCityOfDeparture;
    }

    public void setIdCityOfDeparture(int idCityOfDeparture) {
        this.idCityOfDeparture = idCityOfDeparture;
    }

    public int getIdCityOfArrived() {
        return idCityOfArrived;
    }

    public void setIdCityOfArrived(int idCityOfArrived) {
        this.idCityOfArrived = idCityOfArrived;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PopularInquiries that = (PopularInquiries) o;

        if (idCityOfDeparture != that.idCityOfDeparture) return false;
        if (idCityOfArrived != that.idCityOfArrived) return false;
        return count == that.count;

    }

    @Override
    public int hashCode() {
        int result = idCityOfDeparture;
        result = 31 * result + idCityOfArrived;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "PopularInquiries{" +
                "idCityOfDeparture=" + idCityOfDeparture +
                ", idCityOfArrived=" + idCityOfArrived +
                ", count=" + count +
                '}';
    }
}
