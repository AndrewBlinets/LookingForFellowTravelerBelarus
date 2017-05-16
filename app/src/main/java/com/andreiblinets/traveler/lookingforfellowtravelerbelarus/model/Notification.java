package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;


public class Notification extends BaseClass {

    private long idUser;
    private String type;
    private String text;
    private String status;
    private String dataTime;

    public Notification() {
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (idUser != that.idUser) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return dataTime != null ? dataTime.equals(that.dataTime) : that.dataTime == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dataTime != null ? dataTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idUser=" + idUser +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                ", dataTime='" + dataTime + '\'' +
                '}';
    }
}
