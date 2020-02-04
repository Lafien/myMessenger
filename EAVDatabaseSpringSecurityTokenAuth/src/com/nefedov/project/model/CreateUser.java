package com.nefedov.project.model;

import java.util.List;

public class CreateUser {
    private String username;
    private String password;
    private Role roles;
    private String surname;
    private String firstname;

    public CreateUser(String username, String password, Role roles, String surname, String firstname) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.surname = surname;
        this.firstname = firstname;
    }

    public CreateUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
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
        return "CreateUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
