package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class LastUpdate extends BaseClass {

    private String lastUpdateCity;
    private String lastUpdateRegion;
    private String lastUpdateCountry;

    public LastUpdate() {
        super();
    }

    public String getLastUpdateCity() {
        return lastUpdateCity;
    }

    public void setLastUpdateCity(String lastUpdateCity) {
        this.lastUpdateCity = lastUpdateCity;
    }

    public String getLastUpdateRegion() {
        return lastUpdateRegion;
    }

    public void setLastUpdateRegion(String lastUpdateRegion) {
        this.lastUpdateRegion = lastUpdateRegion;
    }

    public String getLastUpdateCountry() {
        return lastUpdateCountry;
    }

    public void setLastUpdateCountry(String lastUpdateCountry) {
        this.lastUpdateCountry = lastUpdateCountry;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastUpdate that = (LastUpdate) o;

        if (lastUpdateCity != null ? !lastUpdateCity.equals(that.lastUpdateCity) : that.lastUpdateCity != null)
            return false;
        if (lastUpdateRegion != null ? !lastUpdateRegion.equals(that.lastUpdateRegion) : that.lastUpdateRegion != null)
            return false;
        if (lastUpdateCountry != null ? !lastUpdateCountry.equals(that.lastUpdateCountry) : that.lastUpdateCountry != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastUpdateCity != null ? lastUpdateCity.hashCode() : 0;
        result = 31 * result + (lastUpdateRegion != null ? lastUpdateRegion.hashCode() : 0);
        result = 31 * result + (lastUpdateCountry != null ? lastUpdateCountry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LastUpdate{" +
                "lastUpdateCity='" + lastUpdateCity + '\'' +
                ", lastUpdateRegion='" + lastUpdateRegion + '\'' +
                ", lastUpdateCountry='" + lastUpdateCountry + '\'' +
                '}';
    }
}
