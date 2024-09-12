package com.mealplanner.service;

import com.mealplanner.dao.IngredientRepository;
import com.mealplanner.dao.MealRepository;
import com.mealplanner.entity.Ingredient;
import com.mealplanner.entity.Meal;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService{
    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, IngredientRepository ingredientRepository){
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public Meal save(Meal meal) {
        if (meal.getMealDescription() != null) {
            meal.getMealDescription().setMeal(meal);
        }
        for (Ingredient ingredient : meal.getIngredients()) {
            ingredientRepository.save(ingredient);
        }
        return mealRepository.save(meal);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Meal> findAllMealsWithCategory() {
        return mealRepository.findAllWithCategory();
    }

}
