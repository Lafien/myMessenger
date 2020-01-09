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
    public List<UserSecurity> getAll() {
        List<UserSecurity> result = userDAO.getAllUserSecurity();
        return result;
    }

    @Override
    public UserSecurity  findByUsername(String username) {
        UserSecurity result = userDAO.userForSecurity(username);
        return result;
    }

    @Override
    public UserInfo findByUsernameInfo(String username) {
        UserInfo result = userDAO.findInfoAboutUser(username);
        return result;
    }

    @Override
    public List<UserInfo> findFriendContact(String username) {
        List<UserInfo> result = userDAO.getFriendContact(username);
        return result;
    }


}
