package com.mealplanner.controller;

import com.mealplanner.dto.IngredientDTO;
import com.mealplanner.entity.*;
import com.mealplanner.service.CategoryService;
import com.mealplanner.service.MealService;
import com.mealplanner.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String saveMeal(@ModelAttribute Meal meal,
                           @RequestParam(value = "categoryId", required = false) String categoryId,
                           @RequestParam(value = "ingredients", required = false) List<IngredientDTO> ingredients) {

        // Handle category

        if (categoryId != null){
            int catId = Integer.parseInt(categoryId);
            Category category = categoryService.findById(catId);
            meal.setCategory(category);
        }


        if (ingredients != null) {
            List<Ingredient> ingredientList = new ArrayList<>();
            for (IngredientDTO ing : ingredients) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredient(ing.getName());
                ingredient.setQuantity(ing.getQuantity());
                Measure measure = measureService.findById(ing.getMeasureId());
                ingredient.setMeasure(measure);
                ingredient.setMeal(meal);
                ingredientList.add(ingredient);
            }
            meal.setIngredients(ingredientList);
        }

        mealService.save(meal);

        return "redirect:/meals/list";
    }

    @GetMapping("/mymeal")
    public String showMeal(){
        return "show-meal";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }







}
