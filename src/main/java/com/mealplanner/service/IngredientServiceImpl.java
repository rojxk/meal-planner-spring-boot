package com.mealplanner.service;

import com.mealplanner.dao.IngredientRepository;
import com.mealplanner.entity.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
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
}
