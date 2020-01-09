package com.nefedov.project.rest;

import com.nefedov.project.model.UserInfo;
import com.nefedov.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/users/")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "getusername")
    public ResponseEntity<String> getUserById(Authentication authentication){
        String username = authentication.getName();
        return new ResponseEntity<>(username,HttpStatus.OK);
    }

    @GetMapping(value = "users/getfriends")
    public ResponseEntity<List<UserInfo>> getFriendsContact(Authentication authentication) {
        List<UserInfo> user = userService.findFriendContact(authentication.getName());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
