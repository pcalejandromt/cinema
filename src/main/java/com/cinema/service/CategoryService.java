
package com.cinema.service;

import com.cinema.model.Category;
import com.cinema.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
     public List<Category> getAll(){
      return categoryRepository.getAll();
    }
    
     public Optional<Category> getCategory(int id){
         return categoryRepository.getCategory(id);
     }
     
     public Category save(Category c){
         if(c.getId()==null){
             return categoryRepository.save(c);
         }else{
             Optional ce = categoryRepository.getCategory(c.getId());
             if(ce.isPresent()){
                return c;
            }else{
                return categoryRepository.save(c);
            }  
         }
     }
}
