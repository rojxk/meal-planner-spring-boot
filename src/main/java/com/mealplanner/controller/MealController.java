package com.mealplanner.controller;

import com.mealplanner.entity.*;
import com.mealplanner.service.CategoryService;
import com.mealplanner.service.IngredientService;
import com.mealplanner.service.MealService;
import com.mealplanner.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/meals")
public class MealController {

    private static final Logger logger = LoggerFactory.getLogger(MealController.class);


    private MealService mealService;
    private CategoryService categoryService;
    private MeasureService measureService;

    private IngredientService ingredientService;

    @Autowired
    public MealController(MealService mealService, CategoryService categoryService, MeasureService measureService, IngredientService ingredientService){
        this.mealService = mealService;
        this.categoryService = categoryService;
        this.measureService = measureService;
        this.ingredientService = ingredientService;
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
                           @RequestParam(value = "categoryId", required = false) String categoryId) {

        // Handle category
        if (categoryId != null){
            logger.debug("inside category");
            int catId = Integer.parseInt(categoryId);
            Category category = categoryService.findById(catId);
            meal.setCategory(category);
        }
        logger.debug("After category");



        // Process ingredients and their measures
        List<Ingredient> ingredients = meal.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            logger.debug("Processing {} ingredients", ingredients.size());

            for (Ingredient ingredient : ingredients) {
                if (ingredient.getMeasure() != null && ingredient.getMeasure().getId() != null) {
                    logger.debug("Processing measure for ingredient: {}", ingredient.getIngredient());
                    logger.debug(ingredient.getMeasure().toString());
                    Measure measure = measureService.findById(ingredient.getMeasure().getId());
                    ingredient.setMeasure(measure);
                }

                logger.debug(ingredient.toString());
            }
        } else {
            logger.warn("No ingredients provided in the meal");
        }

        logger.debug("Before save");

        mealService.save(meal);

        return "redirect:/meals/list";
    }

    @GetMapping("/show-meal")
    public String showMeal(@RequestParam("mealId") Integer mealId, Model theModel){
        Meal meal = mealService.findMealWithAllInfoById(mealId);
        if (meal != null){
            theModel.addAttribute("meal", meal);
            return "meals/show-meal";
        } else {
            return "redirect:/meals/list";
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}
