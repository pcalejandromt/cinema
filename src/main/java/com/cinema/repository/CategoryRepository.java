
package com.cinema.repository;

import com.cinema.model.Category;
import com.cinema.repository.crudrepository.CategoryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;
    
    public List<Category> getAll(){
        return (List<Category>) categoryCrudRepository.findAll();
    }
    
    public Optional<Category> getCategory(int id){
        return categoryCrudRepository.findById(id);        
    }
    
    public Category save(Category c){
        return categoryCrudRepository.save(c);
    }
    
    public void delete(Category c){
        categoryCrudRepository.delete(c);
    }
    
}
