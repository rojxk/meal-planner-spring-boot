package com.mealplanner.dao;

import com.mealplanner.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Integer> {

    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.category")
    List<Meal> findAllWithCategory();

    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.category LEFT JOIN FETCH m.ingredients LEFT JOIN FETCH m.mealDescription WHERE m.id = :id")
    Meal findMealWithAllInfoById(Integer id);

}
