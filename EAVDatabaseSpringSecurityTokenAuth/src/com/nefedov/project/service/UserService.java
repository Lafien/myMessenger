package com.nefedov.project.service;

import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;

import java.util.List;


public interface UserService {


    UserSecurity findByUsername(String username);

    UserInfo findByUsernameInfo(String username);

    List<UserInfo> findFriendContact(String username);

    void addContact(String username, String usernameContact);

    List<UserInfo> getChats(String username);

    void changeSurname(String surname, String username);

    void changeFisrtname(String firstname, String username);

    void addSurname(String surname, String username);

    void addFirstname(String firstname, String username);

    void createUser(String username, String password, String role);


}
