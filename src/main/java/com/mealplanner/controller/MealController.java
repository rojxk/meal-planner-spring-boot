package com.mealplanner.controller;

import com.mealplanner.entity.Category;
import com.mealplanner.entity.Ingredient;
import com.mealplanner.entity.Meal;
import com.mealplanner.entity.MealDescription;
import com.mealplanner.service.CategoryService;
import com.mealplanner.service.MealService;
import com.mealplanner.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/meals")
public class MealController {

    private MealService mealService;

    private CategoryService categoryService;
    private MeasureService measureService;


    @Autowired
    public MealController(MealService mealService, CategoryService categoryService,MeasureService measureService){
        this.mealService = mealService;
        this.categoryService = categoryService;
        this.measureService = measureService;
    }


    @GetMapping("/list")
    public String list(Model theModel){
        List<Meal> theMeals = mealService.findAllMealsWithCategory();
        theModel.addAttribute("meals", theMeals);
        return "meals/main-meal-planner";
    }

    @GetMapping("/add-meal")
    public String addMeal(Model theModel) {
        Meal theMeal = new Meal();
        theMeal.setMealDescription(new MealDescription());
        theModel.addAttribute("meal", theMeal);
        theModel.addAttribute("categories", categoryService.findAll());
        theModel.addAttribute("measures", measureService.findAll());
        return "meals/add-meal-form";
    }



    @PostMapping("/save")
    public String saveMeal(@ModelAttribute("meal") Meal theMeal, @RequestParam(value = "categoryId", required = false) String categoryId) {
        int catId = Integer.parseInt(categoryId);
        Category category = categoryService.findById(catId);
        theMeal.setCategory(category);
        for (Ingredient ingredient : theMeal.getIngredients()) {
            ingredient.setMeal(theMeal);
        }


        mealService.save(theMeal);
        return "redirect:/meals/list";
    }





}
