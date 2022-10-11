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

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation r) {
        if (r.getIdReservation() == null) {
            return reservationRepository.save(r);
        } else if (r.getCinema().getId() == null) {
            return r;
        } else if (r.getClient().getIdClient() == null) {
            return r;
        }
        return reservationRepository.save(r);

    }

    public Reservation update(Reservation r) {
        if (r.getIdReservation() != null) {
            Optional<Reservation> or = reservationRepository.getReservation(r.getIdReservation());
            if (!or.isEmpty()) {
                if (r.getStartDate() != null) {
                    or.get().setStartDate(r.getStartDate());
                }
                if (r.getDevolutionDate() != null) {
                    or.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus() != null) {
                    or.get().setStatus(r.getStatus());
                }
                if (r.getCinema() != null) {
                    or.get().setCinema(r.getCinema());
                }
                if (r.getClient() != null) {
                    or.get().setClient(r.getClient());
                }
                if (r.getScore() != null) {
                    or.get().setScore(r.getScore());
                }
                reservationRepository.save(or.get());
                return or.get();
            } else {
                return r;
            }
        } else {
            return r;
        }
    }
    
    public Boolean deleteReservation(int id){
        Optional<Reservation> or=getReservation(id);
        if (or.isPresent()){
            reservationRepository.delete(or.get());
            return true;
        }
        return false;
    }
}
