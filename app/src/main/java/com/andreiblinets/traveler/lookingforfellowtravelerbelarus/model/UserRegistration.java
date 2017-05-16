package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class UserRegistration extends User {

    private String hashPassword;

    public UserRegistration() {
        super();
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserRegistration that = (UserRegistration) o;

        return hashPassword != null ? hashPassword.equals(that.hashPassword) : that.hashPassword == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hashPassword != null ? hashPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "hashPassword='" + hashPassword + '\'' +
                '}';
    }
}
