package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class Message extends BaseClass {

    private long idUser;
    private long idTeam;
    private String message;
    private String dateMessage;

    public Message() {
        super();
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (idUser != message1.idUser) return false;
        if (idTeam != message1.idTeam) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null)
            return false;
        return dateMessage != null ? dateMessage.equals(message1.dateMessage) : message1.dateMessage == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (int) (idTeam ^ (idTeam >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (dateMessage != null ? dateMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idUser=" + idUser +
                ", idTeam=" + idTeam +
                ", message='" + message + '\'' +
                ", dateMessage='" + dateMessage + '\'' +
                '}';
    }
}
