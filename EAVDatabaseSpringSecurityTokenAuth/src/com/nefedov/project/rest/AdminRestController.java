package com.nefedov.project.rest;

import com.nefedov.project.DAO.UserDAO;
import com.nefedov.project.model.UserSecurity;
import com.nefedov.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nefedov.project.utils.EncrytedPasswordUtils.encrytePassword;


@RestController
@RequestMapping(value = "/admin/")
public class AdminRestController {

    @Autowired
    UserDAO userDAO;

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping(value = "users/{username}")
    public ResponseEntity<UserSecurity> getUserByUsername(@PathVariable(name = "username") String username) {
        UserSecurity user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("create/user")
    @ResponseBody
    public String createUser(@RequestBody UserSecurity userSecurity) {
        String encrytedPassword = encrytePassword(userSecurity.getPassword());
        userService.createUser(
                userSecurity.getUsername(), encrytedPassword, userSecurity.getRoles().toString());
        return "success";
    }
}
