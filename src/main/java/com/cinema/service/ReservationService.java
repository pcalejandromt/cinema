package com.cinema.service;

import com.cinema.model.Reservation;
import com.cinema.model.custom.CountClient;
import com.cinema.model.custom.StatusAmount;
import com.cinema.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> optionalreservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (optionalreservation.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
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

    public Boolean deleteReservation(int id) {
        Optional<Reservation> or = getReservation(id);
        if (or.isPresent()) {
            reservationRepository.delete(or.get());
            return true;
        }
        return false;
    }
    
    //Saber cuántas reservas se han hecho en un intervalo de tiempo
    public List<Reservation> getReservationPeriod(String dateOne, String dateTwo) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); //pattern
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = parser.parse(dateOne);
            endDate = parser.parse(dateTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate.before(endDate)) {
            return reservationRepository.getReservationPeriod(startDate, endDate);
        } else {
            return new ArrayList<>();
        }
    }
    
    //Se debe entregar la lista de usuarios con la cantidad reservas completas.
    //Se debe mostrar los clientes que más reservas completas tienen en orden descendente
    public List<CountClient> getTopClient(){
        return reservationRepository.getTopClients();
    }
    
    //	Ver un reporte de reservas con la cantidad de completas vs la cantidad de reservas canceladas
    // Trae las reservas por status  Posibles opciones: created, cancelled, completed
    public StatusAmount getReportStatus(){
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        return new StatusAmount(completed.size(), cancelled.size());
    }
}
