
package com.cinema.repository.crudrepository;

import com.cinema.model.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientCrudRepository extends CrudRepository<Client, Integer> {
    
}
