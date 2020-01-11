package com.nefedov.project.model;

public class UserInfo {

    private String username;
    private String surname;
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



    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstName='" + surname + '\'' +
                ", secondName='" + firstname + '\'' +
                '}';
    }
}
