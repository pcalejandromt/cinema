
package com.cinema.repository.crudrepository;

import com.cinema.model.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    
     //Saber cuántas reservas se han hecho en un intervalo de tiempo
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date startDate, Date endDate);
    
    //	Ver un reporte de reservas con la cantidad de completas vs la cantidad de reservas canceladas
    // Trae las reservas por status  Posibles opciones: created, cancelled, completed
    public List<Reservation> findAllByStatus(String status);
    
    //Se debe entregar la lista de usuarios con la cantidad reservas completas.
    //Se debe mostrar los clientes que más reservas completas tienen en orden descendente.
    @Query("select c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client) desc")
    public List<Object[]> countTotalReservationByClient(); // llamdo en JPQL
           
}
