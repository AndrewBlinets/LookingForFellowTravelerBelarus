package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Team extends BaseClass{

    private long idUser;
    private long idTrip;

    public Team() {
        super();
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(long idTrip) {
        this.idTrip = idTrip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (idUser != team.idUser) return false;
        return idTrip == team.idTrip;

    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (int) (idTrip ^ (idTrip >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idUser=" + idUser +
                ", idTrip=" + idTrip +
                '}';
    }
}
