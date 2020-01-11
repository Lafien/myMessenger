package com.nefedov.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nefedov.project.model.Role;
import com.nefedov.project.model.UserSecurity;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private String username;
    private List<Role> role;

    public UserSecurity toUser() {
        UserSecurity user = new UserSecurity();
        user.setUsername(username);
        user.setRoles(role);
        return user;
    }

    public static AdminUserDto fromUser(UserSecurity user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setRole(user.getRoles());
        return adminUserDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }


}
