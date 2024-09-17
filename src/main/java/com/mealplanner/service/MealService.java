package com.mealplanner.service;

import com.mealplanner.entity.Meal;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MealService {

    List<Meal> findAll();
    Meal findById(Integer id);
    Meal save(Meal meal);
    void deleteById(int id);

    List<Meal> findAllMealsWithCategory();

    Meal findMealWithAllInfoById(Integer id);



}
