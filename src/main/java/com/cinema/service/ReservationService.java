package com.cinema.service;

import com.cinema.model.Reservation;
import com.cinema.repository.ReservationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    
     public List<Reservation> getAll(){
      return reservationRepository.getAll();
    }
    
     public Optional<Reservation> getReservation(int id){
         return reservationRepository.getReservation(id);
     }
     
     public Reservation save(Reservation r){
         if(r.getIdReservation()==null){
             return reservationRepository.save(r);
         }else if(r.getCinema().getId()==null){
                return r;
         }else if(r.getClient().getIdClient()==null){
                return r;
         }  
         return reservationRepository.save(r);
         
     }
}