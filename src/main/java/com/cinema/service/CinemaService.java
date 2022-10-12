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
    
    public Cinema save(Cinema cinema){
        if(cinema.getId()==null){
            return cinemaRepository.save(cinema);
        }else{
            Optional<Cinema> ce = cinemaRepository.getCinema(cinema.getId()); // Trae el cinemaRepo con Id leido
            if(ce.isEmpty()){
                return cinemaRepository.save(cinema);                
            }else{
                return cinema;
            }    
        }
    }
    
    public Cinema update(Cinema cinema){
        if(cinema.getId()!=null){
            Optional<Cinema> optionalCinema = cinemaRepository.getCinema(cinema.getId());
            if(!optionalCinema.isEmpty()){
                if(cinema.getName()!=null){
                    optionalCinema.get().setName(cinema.getName());
                }
                if(cinema.getOwner()!=null){
                    optionalCinema.get().setOwner(cinema.getOwner());
                }
                if(cinema.getCapacity()!=null){
                    optionalCinema.get().setCapacity(cinema.getCapacity());
                }
                if(cinema.getDescription()!=null){
                    optionalCinema.get().setDescription(cinema.getDescription()); //*
                }
                if(cinema.getCategory()!=null){
                    optionalCinema.get().setCategory(cinema.getCategory());
                }
                if(cinema.getMessages()!=null){
                    optionalCinema.get().setMessages(cinema.getMessages());
                }
                cinemaRepository.save(optionalCinema.get());
                return optionalCinema.get();
            }else{
                return cinema;
            }
        }else{
            return cinema;
        }
    }    
    
    public boolean deleteCinema(int id){
        //Boolean aBoolean = getCinema(id).map(Cinema ->{cinemaRepository.delete(Cinema);return true;}).orElse(other: false);
        Optional<Cinema> optionalCinema= getCinema(id);
        if(optionalCinema.isPresent()){
            cinemaRepository.delete(optionalCinema.get());
            return true;
        }
        return false;
    }
}
