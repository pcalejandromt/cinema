
package com.cinema.controller;

import com.cinema.model.Reservation;
import com.cinema.model.custom.CountClient;
import com.cinema.model.custom.StatusAmount;
import com.cinema.service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
        
    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId){
        return reservationService.getReservation(reservationId);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation r){
        return reservationService.save(r);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation r){
        return reservationService.update(r);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean  deleteReservation(@PathVariable("id") int id){
        return reservationService.deleteReservation(id);  
    }    
    //Reto 5
    //Saber cuántas reservas se han hecho en un intervalo de tiempo    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getByDates(@PathVariable("dateOne") String dateOne,@PathVariable("dateTwo") String dateTwo){
        return reservationService.getReservationPeriod(dateOne, dateTwo);  
    }
    
    //	Ver un reporte de reservas con la cantidad de completas vs la cantidad de reservas canceladas
    // Trae las reservas por status  Posibles opciones: created, cancelled, completed
    @GetMapping("/report-status")
    public StatusAmount getReportStatus(){
        return reservationService.getReportStatus();
    }
    
    //Se debe entregar la lista de usuarios con la cantidad reservas completas.
    //Se debe mostrar los clientes que más reservas completas tienen en orden descendente
    @GetMapping("/report-clients")
    public List<CountClient> getReportClients(){
       return reservationService.getTopClient();
    }
} 
