package com.nefedov.project.rest;

import com.nefedov.project.model.Message;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.service.MessageService;
import com.nefedov.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/users/")
public class UserRestController {

    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public UserRestController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }




    @GetMapping(value = "getusername")
    public ResponseEntity<String> getUserById(Authentication authentication){
        String username = authentication.getName();
        return new ResponseEntity<>(username,HttpStatus.OK);
    }

    @GetMapping(value = "getfriends")
    public ResponseEntity<List<UserInfo>> getFriendsContact(Authentication authentication) {
        List<UserInfo> user = userService.findFriendContact(authentication.getName());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "myprofile")
    public ResponseEntity<UserInfo> getMyProfile(Authentication authentication) {
        UserInfo user = userService.findByUsernameInfo(authentication.getName());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "infoaboutcontact")
    public ResponseEntity<UserInfo> getInfoAboutFriend(@RequestParam(name = "username") String username) {
        UserInfo user = userService.findByUsernameInfo(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("addcontact")
    @ResponseBody
    public String createUser(Authentication authentication, @RequestParam(name = "usernameContact") String usernameContact) {
        userService.addContact(authentication.getName(), usernameContact);
        return "success";
    }

    @GetMapping(value = "chats")
    public ResponseEntity<List<UserInfo>> getChats(Authentication authentication) {
        List<UserInfo> user = userService.getChats(authentication.getName());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "chats/{username}")
    public ResponseEntity<List<Message>> getMessagesInChat(Authentication authentication, @PathVariable(name = "username") String username) {
        List<Message> user = messageService.getMessagesInChat(authentication.getName(), username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "chats/new-message")
    public void createMessage(@RequestBody Message message, Authentication authentication) {
        messageService.createMessage(message.getText(), authentication.getName(), message.getMsgTo());
    }

}
