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

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }
    
    public boolean deleteCategory(int id){
       Optional<Category> oc=getCategory(id);
       if(!oc.isEmpty()){
           categoryRepository.delete(oc.get());
           return true;
       }
       return false;
    }
    public Category update(Category c){
        if (c.getId()!=null){
            Optional<Category> ca = categoryRepository.getCategory(c.getId());
            if (!ca.isEmpty()){
                if(c.getName()!=null){
                    ca.get().setName(c.getName());
                }
                if(c.getDescription()!=null){
                    ca.get().setDescription(c.getDescription());
                }
                if(c.getCinemas()!=null){
                    ca.get().setCinemas(c.getCinemas());
                }
                categoryRepository.save(ca.get());
                return ca.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }
    public Category save(Category c) {
        if (c.getId() == null) {
            return categoryRepository.save(c);
        } else {
            Optional ce = categoryRepository.getCategory(c.getId());
            if (ce.isPresent()) {
                return c;
            } else {
                return categoryRepository.save(c);
            }
        }
    }
}
