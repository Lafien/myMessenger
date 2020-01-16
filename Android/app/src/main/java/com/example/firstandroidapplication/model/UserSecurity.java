package com.example.firstandroidapplication.model;

import com.google.gson.annotations.SerializedName;

public class UserSecurity {

    @SerializedName("username")
    private String username;

    @SerializedName("token")
    private String token;

    public UserSecurity(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserSecurity{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
