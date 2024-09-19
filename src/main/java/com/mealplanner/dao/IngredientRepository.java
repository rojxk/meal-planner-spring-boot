package com.mealplanner.dao;

import com.mealplanner.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("SELECT i FROM Ingredient i LEFT JOIN FETCH i.measure")
    List<Ingredient> findAllWithMeasure();

    @Query("SELECT i FROM Ingredient i LEFT JOIN FETCH i.meal WHERE i.meal.id =:id")
    List<Ingredient> findAllByMealId(Integer id);

}
