package com.nefedov.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nefedov.project.model.UserSecurity;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public UserSecurity toUser(){
        UserSecurity user = new UserSecurity();
        user.setUsername(username);

        return user;
    }

    public static UserDto fromUser(UserSecurity user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
