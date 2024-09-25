package com.mealplanner.service;

import com.mealplanner.dao.IngredientRepository;
import com.mealplanner.dao.MealRepository;
import com.mealplanner.entity.Ingredient;
import com.mealplanner.entity.Meal;
import com.mealplanner.util.MealComparator;
import com.mealplanner.util.MealSortCriteria;
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
    public void deleteById(Integer id) {
        if (id != null){
            mealRepository.deleteById(id);
        }

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

    @Override
    @Transactional
    public Meal update(Meal meal) {
        // Save the meal first to ensure it has an ID
        mealRepository.save(meal);

        // Handle ingredients
        if (meal.getIngredients() != null) {
            for (Ingredient ingredient : meal.getIngredients()) {
                if (ingredient.isDeleted()) {
                    // Remove the ingredient if it's marked as deleted
                    if (ingredient.getId() != null) {
                        ingredientRepository.deleteById(ingredient.getId());
                    }
                } else {
                    // Save or update the ingredient
                    ingredient.setMeal(meal);
                    ingredientRepository.save(ingredient);
                }
            }
        }

        return meal;
    }

    @Override
    public List<Meal> findAllWithCategoryByUserId(Integer id) {
        return mealRepository.findAllWithCategoryByUserId(id);
    }

    @Override
    public List<Meal> sortedMeals(Integer userId, String sortBy) {
        List<Meal> meals = mealRepository.findAllWithCategoryByUserId(userId);
        MealSortCriteria criteria = switch (sortBy.toLowerCase()) {
            case "newest" -> MealSortCriteria.NEWEST;
            case "name_az" -> MealSortCriteria.NAME_AZ;
            case "name_za" -> MealSortCriteria.NAME_ZA;
            case "category" -> MealSortCriteria.CATEGORY;
            case "making_time" -> MealSortCriteria.MAKING_TIME;
            case "portions" -> MealSortCriteria.PORTIONS;
            default -> MealSortCriteria.DEFAULT;
        };

        meals.sort(new MealComparator(criteria));
        return meals;
    }

}
