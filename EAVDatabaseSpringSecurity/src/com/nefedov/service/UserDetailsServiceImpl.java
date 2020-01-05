package com.nefedov.service;

import com.nefedov.DAO.UserDAO;
import com.nefedov.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO appUserDAO;

    /*@Autowired
    private AppRoleDAO appRoleDAO;*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserSecurity> list = this.appUserDAO.userForSecurity(userName);

        if (list == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + list);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = new ArrayList<>();
        for(int i =0; i < list.size(); i++){
            roleNames.add(list.get(i).getRole());
        }

        //roleNames.add("ROLE_ADMIN");



        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(list.get(0).getUsername(), //
                list.get(0).getPassword(), grantList);

        return userDetails;
    }

}