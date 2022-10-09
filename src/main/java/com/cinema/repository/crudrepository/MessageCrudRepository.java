
package com.cinema.repository.crudrepository;

import com.cinema.model.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
    
}
