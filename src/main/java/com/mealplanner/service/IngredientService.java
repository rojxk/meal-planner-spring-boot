package com.mealplanner.service;

import com.mealplanner.entity.Ingredient;

import java.util.List;


public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient findById(Integer id);
    Ingredient save(Ingredient ingredient);
    void deleteById(int id);

    void deleteByMealId(Integer id);
}
