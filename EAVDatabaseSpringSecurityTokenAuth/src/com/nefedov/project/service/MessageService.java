package com.nefedov.project.service;


import com.nefedov.project.model.Message;
import com.nefedov.project.model.UserInfo;
import com.nefedov.project.model.UserSecurity;

import java.util.List;


public interface MessageService {

     List<Message> getMessagesInChat(String msgOwner, String msgTo);

     void createMessage(String textMessage, String msgFrom, String msgTo);

}
