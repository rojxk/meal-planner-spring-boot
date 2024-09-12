package com.mealplanner.service;

import com.mealplanner.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    void deleteById(int id);
}
