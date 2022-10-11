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

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client update(Client c) {
        if (c.getIdClient() != null) {
            Optional<Client> cl = clientRepository.getClient(c.getIdClient());
            if (!cl.isEmpty()) {
                if (c.getEmail() != null) {
                    cl.get().setEmail(c.getEmail());
                }
                if (c.getPassword() != null) {
                    cl.get().setPassword(c.getPassword());
                }
                if (c.getName() != null) {
                    cl.get().setName(c.getName());
                }
                if (c.getAge() != null) {
                    cl.get().setAge(c.getAge());
                }
                clientRepository.save(cl.get());
                return cl.get();
            } else {
                return c;
            }
            
        }else{
            return c;
        }
    }    
    
    public Client save(Client c) {
        if (c.getIdClient() == null) {
            return clientRepository.save(c);
        } else {
            Optional<Client> c1 = clientRepository.getClient(c.getIdClient());
            if (c1.isEmpty()) {
                return clientRepository.save(c);
            } else {
                return c;
            }
        }

    }
    
    public Boolean deleteClient(int id){
        Optional<Client> oc=getClient(id);
        if(oc.isPresent()){
            clientRepository.delete(oc.get());
            return true;
        }
        return false;
    }
}
