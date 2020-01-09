package com.nefedov.project.service;


import com.nefedov.project.DAO.UserDAO;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;

import java.util.List;


public interface UserService {


    List<UserSecurity> getAll();

    UserSecurity findByUsername(String username);

    UserInfo findByUsernameInfo(String username);

    List<UserInfo> findFriendContact(String username);

}
