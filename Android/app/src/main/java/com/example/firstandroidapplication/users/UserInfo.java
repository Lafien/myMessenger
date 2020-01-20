package com.example.firstandroidapplication.users;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("username")
    private String username;

    @SerializedName("surname")
    private String surname;

    @SerializedName("firstname")
    private String firstname;

    public UserInfo(String username, String surname, String firstname) {
        this.username = username;
        this.surname = surname;
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    /*@Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstName='" + surname + '\'' +
                ", secondName='" + firstname + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return   surname + " " + firstname + " (" + username + ")";

    }
}
