package com.cinema.service;

import com.cinema.model.Client;
import com.cinema.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired 
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);        
    }
   
    public Client save(Client c) {
        if(c.getIdClient()==null){
            return clientRepository.save(c);
        }else {
            Optional<Client> c1=clientRepository.getClient(c.getIdClient());
            if(c1.isEmpty()){
                return clientRepository.save(c);
            }else{
                return c;
            }
        }       
        
    }
}