package com.mealplanner.service;

import com.mealplanner.dao.IngredientRepository;
import com.mealplanner.dao.MealRepository;
import com.mealplanner.entity.Ingredient;
import com.mealplanner.entity.Meal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, MealRepository mealRepository){
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public Ingredient findById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient) {

        if (ingredient.getMeal() != null) {
            ingredient.getMeal().getIngredients().add(ingredient);
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByMealId(Integer id) {
        List<Ingredient> ingredients = ingredientRepository.findAllByMealId(id);
        ingredientRepository.deleteAll(ingredients);

    }
}
