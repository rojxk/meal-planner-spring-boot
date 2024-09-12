package com.mealplanner.service;

import com.mealplanner.dao.CategoryRepository;
import com.mealplanner.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

    }


    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
