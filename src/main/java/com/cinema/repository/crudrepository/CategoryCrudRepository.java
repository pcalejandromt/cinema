package com.cinema.repository.crudrepository;

import com.cinema.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryCrudRepository extends CrudRepository <Category, Integer>{
    
}
