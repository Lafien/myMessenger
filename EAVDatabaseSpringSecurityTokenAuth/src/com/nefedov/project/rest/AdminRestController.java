package com.nefedov.project.rest;

import com.nefedov.project.DAO.UserDAO;
import com.nefedov.project.model.CreateUser;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;
import com.nefedov.project.service.MessageService;
import com.nefedov.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.nefedov.project.utils.EncrytedPasswordUtils.encrytePassword;


@RestController
@RequestMapping(value = "/admin/")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping(value = "users/{username}")
    public ResponseEntity<UserSecurity> getUserByUsername(@PathVariable(name = "username") String username) {
        UserSecurity user = userService.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "This user does not exist");
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    @ResponseBody
    public String createUser(@RequestBody CreateUser createUser) {

        UserInfo user = userService.findByUsernameInfo(createUser.getUsername());

        if(user==null) {
            String encrytedPassword = encrytePassword(createUser.getPassword());
            userService.createUser(createUser.getUsername(), encrytedPassword, createUser.getRoles().getRole(), createUser.getSurname(), createUser.getFirstname());
            return "success";
        }
        else {
            System.out.println(user.toString());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A user with that username already exists");
        }


    }
}
