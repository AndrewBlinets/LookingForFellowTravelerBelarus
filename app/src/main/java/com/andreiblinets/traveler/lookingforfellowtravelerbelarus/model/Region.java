package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Region extends BaseClass{

    private long idCountry;
    private String name;

    public Region() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (idCountry != region.idCountry) return false;
        return name != null ? name.equals(region.name) : region.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idCountry ^ (idCountry >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Region{" +
                "idCountry=" + idCountry +
                ", name='" + name + '\'' +
                '}';
    }
}
