package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model;

public class User extends BaseClass {

    private String name;
    private String surName;
    private String foto;
    private long indefication;
    private String phone;
    private String email;
    private int rating;
    private String dataRegistration;
    private String aboutUser;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public long getIndefication() {
        return indefication;
    }

    public void setIndefication(long indefication) {
        this.indefication = indefication;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDataRegistration() {
        return dataRegistration;
    }

    public void setDataRegistration(String dataRegistration) {
        this.dataRegistration = dataRegistration;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (indefication != user.indefication) return false;
        if (rating != user.rating) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surName != null ? !surName.equals(user.surName) : user.surName != null) return false;
        if (foto != null ? !foto.equals(user.foto) : user.foto != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (dataRegistration != null ? !dataRegistration.equals(user.dataRegistration) : user.dataRegistration != null)
            return false;
        return aboutUser != null ? aboutUser.equals(user.aboutUser) : user.aboutUser == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        result = 31 * result + (int) (indefication ^ (indefication >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (dataRegistration != null ? dataRegistration.hashCode() : 0);
        result = 31 * result + (aboutUser != null ? aboutUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", foto='" + foto + '\'' +
                ", indefication=" + indefication +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", dataRegistration='" + dataRegistration + '\'' +
                ", aboutUser='" + aboutUser + '\'' +
                '}';
    }
}
