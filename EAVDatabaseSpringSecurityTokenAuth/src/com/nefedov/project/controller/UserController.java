package com.nefedov.project.controller;


import com.nefedov.project.DAO.RoleDAO;
import com.nefedov.project.DAO.UserDAO;
import com.nefedov.project.model.CreateUser;
import com.nefedov.project.model.Role;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.nefedov.project.utils.EncrytedPasswordUtils.encrytePassword;


//not used
@Controller
public class UserController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;


    @PostMapping("/info/about/user")
    @ResponseBody
    public String aboutUser(@RequestParam(name = "login") String login) {
        UserInfo user = userDAO.findInfoAboutUser(login);
        return user.toString();
    }

    @GetMapping("/info/about/role")
    @ResponseBody
    public String getRole() {
        List<Role> list = roleDAO.getRoleDAO();
        for(int i =0; i < list.size();i++) {
            System.out.println(list.get(i));
        }
        return list.toString();
    }


    @PostMapping("/info/about/user/security")
    @ResponseBody
    public String userSecurity(@RequestParam(name = "login") String login) {
        UserSecurity userSecurity = userDAO.userForSecurity(login);
        System.out.println(userSecurity.toString());
        return userSecurity.toString();
    }


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
