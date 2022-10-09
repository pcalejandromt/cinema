
package com.cinema.repository.crudrepository;

import com.cinema.model.Cinema;
import org.springframework.data.repository.CrudRepository;


public interface CinemaCrudRepository extends CrudRepository<Cinema, Integer> {
    
}
