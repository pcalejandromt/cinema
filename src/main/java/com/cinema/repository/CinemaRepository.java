
package com.cinema.repository;

import com.cinema.model.Cinema;
import com.cinema.repository.crudrepository.CinemaCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CinemaRepository {
    @Autowired
    private CinemaCrudRepository cinemaCrudRepository;
    
    public List<Cinema> getAll(){
        return (List<Cinema>) cinemaCrudRepository.findAll();
    }
    
    public Optional<Cinema> getCinema(int id){
        return cinemaCrudRepository.findById(id);
    }
    
    public Cinema save(Cinema c){
        return cinemaCrudRepository.save(c);
    }
    
    public void delete(Cinema c){
        cinemaCrudRepository.delete(c);
    }
    
}
