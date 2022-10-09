
package com.cinema.repository;

import com.cinema.model.Client;
import com.cinema.model.Message;
import com.cinema.repository.crudrepository.MessageCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageCrudRepository;
    
    public List<Message> getAll(){
        return (List<Message>) messageCrudRepository.findAll();
    }
    
    public Optional<Message> getMessage(int id){
        return messageCrudRepository.findById(id);
    }
    
    public Message save(Message m){
        return messageCrudRepository.save(m);
    }
    
    public void delete(Message m){
        messageCrudRepository.delete(m);
    }
    
}
