package com.cinema.service;

import com.cinema.model.Cinema;
import com.cinema.repository.CinemaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;
    
    public List<Cinema> getAll(){
        return cinemaRepository.getAll();
    }
    
    public Optional<Cinema> getCinema(int id){
        return cinemaRepository.getCinema(id);
    }
    
    public Cinema save(Cinema c){
        if(c.getId()==null){
            return cinemaRepository.save(c);
        }else{
            Optional ce = cinemaRepository.getCinema(c.getId()); // Trae el cinemaRepo con Id leido
            if(ce.isPresent()){
                return c;
            }else{
                return cinemaRepository.save(c);
            }    
        }
    }
    
    public Cinema update(Cinema c){
        if(c.getId()!=null){
            Optional<Cinema> ce = cinemaRepository.getCinema(c.getId());
            if(!ce.isEmpty()){
                if(c.getName()!=null){
                    ce.get().setName(c.getName());
                }
                if(c.getCapacity()!=null){
                    ce.get().setCapacity(c.getCapacity());
                }
                if(c.getOwner()!=null){
                    ce.get().setOwner(c.getOwner());
                }
                if(c.getCategory()!=null){
                    ce.get().setCategory(c.getCategory());
                }
                cinemaRepository.save(ce.get());
                return ce.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }    
    
    public boolean deleteCinema(int id){
        //Boolean aBoolean = getCinema(id).map(Cinema ->{cinemaRepository.delete(Cinema);return true;}).orElse(other: false);
        Boolean aBoolean =false;
        Optional<Cinema> c= cinemaRepository.getCinema(id);
        if(c.isPresent()){
            cinemaRepository.delete(c.get());
            aBoolean=true;
        }
        return aBoolean;
    }
}
