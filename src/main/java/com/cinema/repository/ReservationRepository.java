
package com.cinema.repository;

import com.cinema.model.Client;
import com.cinema.model.Reservation;
import com.cinema.model.custom.CountClient;
import com.cinema.repository.crudrepository.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }
    
    public Reservation save(Reservation r){
        return reservationCrudRepository.save(r);
    }
    
    public void delete(Reservation r){
        reservationCrudRepository.delete(r);
    }
    
    //Saber cuántas reservas se han hecho en un intervalo de tiempo
    public List<Reservation> getReservationPeriod(Date startDate, Date endDate) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(startDate, endDate);        
    }
    
    //	Ver un reporte de reservas con la cantidad de completas vs la cantidad de reservas canceladas
    // Trae las reservas por status  Posibles opciones: created, cancelled, completed
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status); //
    }
    
    
    
    //Se debe entregar la lista de usuarios con la cantidad reservas completas.
    //Se debe mostrar los clientes que más reservas completas tienen en orden descendente
    public List<CountClient> getTopClients(){
        List<CountClient> rs = new ArrayList<>();  // CountClient objeto comodin 
        List<Object[]> report=reservationCrudRepository.countTotalReservationByClient();
        for (int i=0;i < report.size();i++){
            /*
            Client client = (Client)report.get(i)[0];  //SELECT *c.client*, COUNT(c,client)
            Integer cnt = (Integer) report.get(i)[1];  //SELECT c.client, *COUNT(c,client)*
            CountClient countClient= new CountClient(cnt,client);
            rs.add(countClient);*/
            //Sentencia equivalente resumida
            rs.add(new CountClient((long)report.get(i)[1], (Client) report.get(i)[0])); //Carga todos los objetos 
        }
        return rs;
        
    }
}
