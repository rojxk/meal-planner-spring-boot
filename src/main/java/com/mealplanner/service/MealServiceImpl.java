package com.mealplanner.service;

import com.mealplanner.dao.IngredientRepository;
import com.mealplanner.dao.MealRepository;
import com.mealplanner.entity.Ingredient;
import com.mealplanner.entity.Meal;
import jakarta.persistence.EntityNotFoundException;
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
        if (id == null) {
            throw new IllegalArgumentException("Meal id cannot be null");
        }
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + id));

    }

    @Override
    @Transactional
    public Meal save(Meal meal) {
        if (meal.getMealDescription() != null) {
            meal.getMealDescription().setMeal(meal);
        }

        if (meal.getIngredients() != null) {
            for (Ingredient ingredient : meal.getIngredients()) {
                ingredient.setMeal(meal);

            }
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

    @Override
    public Meal findMealWithAllInfoById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Meal id cannot be null");
        }
        return mealRepository.findMealWithAllInfoById(id);
    }

}
