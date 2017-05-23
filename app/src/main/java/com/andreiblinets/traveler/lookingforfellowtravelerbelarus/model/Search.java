package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Search // {
{
    private int idCityOfDeparture;
    private int idCityOfArrived;
    private String data;

//    public Search() {
//        super();
//    }


    public Search(int idCityOfDeparture, int idCityOfArrived, String data) {
        this.idCityOfDeparture = idCityOfDeparture;
        this.idCityOfArrived = idCityOfArrived;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        if (idCityOfDeparture != search.idCityOfDeparture) return false;
        if (idCityOfArrived != search.idCityOfArrived) return false;
        return data != null ? data.equals(search.data) : search.data == null;

    }

    @Override
    public int hashCode() {
        int result = idCityOfDeparture;
        result = 31 * result + idCityOfArrived;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Search{" +
                "idCityOfDeparture=" + idCityOfDeparture +
                ", idCityOfArrived=" + idCityOfArrived +
                ", data='" + data + '\'' +
                '}';
    }
}
