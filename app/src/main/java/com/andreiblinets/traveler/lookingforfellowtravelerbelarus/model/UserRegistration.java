package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class UserRegistration  {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private String hashPassword;
    private String foto;
    private String aboutUser;

    public UserRegistration() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surname;
    }

    public void setSurName(String surName) {
        this.surname = surName;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
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
