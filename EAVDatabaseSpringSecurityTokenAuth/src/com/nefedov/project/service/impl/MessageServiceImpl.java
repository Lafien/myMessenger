package com.nefedov.project.service.impl;

import com.nefedov.project.DAO.MessageDAO;
import com.nefedov.project.model.Message;
import com.nefedov.project.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {


    private final MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }


    @Override
    public List<Message> getMessagesInChat(String msgOwner, String msgTo) {
        return messageDAO.getMessagesInChat(msgOwner, msgTo);
    }

    @Override
    public void createMessage(String textMessage, String msgFrom, String msgTo) {
        messageDAO.createMessage(textMessage, msgFrom, msgTo);
    }
}
