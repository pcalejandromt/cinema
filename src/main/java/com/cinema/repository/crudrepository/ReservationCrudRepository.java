
package com.cinema.repository.crudrepository;

import com.cinema.model.Reservation;
import org.springframework.data.repository.CrudRepository;


public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    
}
