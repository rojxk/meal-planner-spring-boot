package com.mealplanner.service;

import com.mealplanner.entity.Meal;
import com.mealplanner.entity.Userdata;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MealService {

    List<Meal> findAll();
    Meal findById(Integer id);
    Meal save(Meal meal);
    void deleteById(Integer id);

    List<Meal> findAllMealsWithCategory();

    Meal findMealWithAllInfoById(Integer id);

    Meal update(Meal meal);

    List<Meal> findAllWithCategoryByUserId(Integer id);

    List<Meal> sortedMeals(Integer userId, String sortBy);

    List<Meal> searchMealsByName(Userdata userdata, String searchTerm);




}
