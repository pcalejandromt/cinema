package com.cinema.service;

import com.cinema.model.Message;
import com.cinema.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message update(Message m) {
        if (m.getIdMessage() != null) {
            Optional<Message> om = messageRepository.getMessage(m.getIdMessage());
            if (!om.isEmpty()) {
                if (m.getMessageText() != null) {
                    om.get().setMessageText(m.getMessageText());
                }
                if (m.getClient() != null) {
                    om.get().setClient(m.getClient());
                }
                if (m.getCinema() != null) {
                    om.get().setCinema(m.getCinema());
                }
                messageRepository.save(om.get());
                return om.get();
            } else {
                return m;
            }
        }
        return m;

    }

    public Message save(Message m) {
        if (m.getIdMessage() == null) {
            return messageRepository.save(m);
        } else {
            Optional<Message> m1 = messageRepository.getMessage(m.getIdMessage());
            if (m1.isEmpty()) {
                return messageRepository.save(m);
            } else {
                return m;
            }
        }

    }
    public Boolean deleteMessage(int id){
        Optional<Message> om = getMessage(id);
        if(om.isPresent()){
            messageRepository.delete(om.get());
            return true;
        }
        return false;
    }
}
