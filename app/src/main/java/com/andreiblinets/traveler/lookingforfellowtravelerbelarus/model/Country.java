package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Country extends BaseClass {

    private String name;
    private String kodCurrency;

    public Country() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKodCurrency() {
        return kodCurrency;
    }

    public void setKodCurrency(String kodCurrency) {
        this.kodCurrency = kodCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return kodCurrency != null ? kodCurrency.equals(country.kodCurrency) : country.kodCurrency == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (kodCurrency != null ? kodCurrency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", kodCurrency='" + kodCurrency + '\'' +
                '}';
    }
}
