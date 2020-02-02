package com.nefedov.project.service.impl;

import com.nefedov.project.DAO.RoleDAO;
import com.nefedov.project.DAO.UserDAO;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;
import com.nefedov.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, BCryptPasswordEncoder passwordEncoder) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserSecurity  findByUsername(String username) {
        return userDAO.userForSecurity(username);
    }

    @Override
    public UserInfo findByUsernameInfo(String username) {
        return userDAO.findInfoAboutUser(username);
    }

    @Override
    public List<UserInfo> findFriendContact(String username) {
        return userDAO.getFriendContact(username);
    }

    @Override
    public void addContact(String username, String usernameContact) {
        userDAO.addContact(username, usernameContact);
    }

    @Override
    public List<UserInfo> getChats(String username) {
        return userDAO.getChats(username);
    }

    @Override
    public void changeSurname(String surname, String username) {
        userDAO.changeSurname(surname, username);
    }

    @Override
    public void changeFisrtname(String firstname, String username) {
        userDAO.changeFirstname(firstname, username);
    }

    @Override
    public void addSurname(String surname, String username) {
        userDAO.addSurname(surname, username);
    }

    @Override
    public void addFirstname(String firstname, String username) {
        userDAO.addFirstname(firstname, username);
    }

    @Override
    public void createUser(String username, String password, String role) {
        userDAO.createUser(username, password, role);
    }


}
