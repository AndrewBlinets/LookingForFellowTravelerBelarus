package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class City extends BaseClass {

    private long idRegion;
    private String name;

    public City() {
        super();
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (idRegion != city.idRegion) return false;
        return name != null ? name.equals(city.name) : city.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idRegion ^ (idRegion >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "idRegion=" + idRegion +
                ", name='" + name + '\'' +
                '}';
    }
}
