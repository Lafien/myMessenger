package com.nefedov.controller;

import com.nefedov.DAO.UserDAO;
import com.nefedov.model.CreateUser;
import com.nefedov.model.UserInfo;
import com.nefedov.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.nefedov.utils.EncrytedPasswordUtils.encrytePassword;

@Controller
public class UserController {

    @Autowired
    UserDAO userDAO;


    @PostMapping("/info/about/user")
    @ResponseBody
    public String aboutUser(@RequestParam(name = "login") String login) {
        List<UserInfo> list = userDAO.findInfoAboutUser(login);
        for(int i =0; i < list.size();i++) {
            System.out.println(list.get(i));
        }
        return list.toString();
    }

    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("/info/about/user/security")
    @ResponseBody
    public String userSecurity(@RequestParam(name = "login") String login) {
        List<UserSecurity> list = userDAO.userForSecurity(login);
        for(int i =0; i < list.size();i++) {
            System.out.println(list.get(i));
        }
        return list.toString();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/user")
    @ResponseBody
    public String createUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "role") String role) {
        String encrytedPassword = encrytePassword(password);
        userDAO.createUser(username, encrytedPassword, role);
        return "success";
    }


    @GetMapping("/createUser")
    public String userForm(Model model) {
        model.addAttribute("createuser", new CreateUser());
        return "createUser";
    }

    @RequestMapping(value="/createUser", method= RequestMethod.POST, params="action=createUser")
    public String createUser(CreateUser createUser) throws IOException {
        //System.out.println(createUser.getPassword() + " " + createUser.getUsername() + " " + createUser.getRole());
        String encrytedPassword = encrytePassword(createUser.getPassword());
        userDAO.createUser(createUser.getUsername(), encrytedPassword, createUser.getRole());
            return "redirect:/createUser";


    }

}
