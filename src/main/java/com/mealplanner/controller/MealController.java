package com.mealplanner.controller;

import com.mealplanner.entity.*;
import com.mealplanner.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/{username}/meals")
public class MealController {

    private static final Logger logger = LoggerFactory.getLogger(MealController.class);


    private MealService mealService;
    private CategoryService categoryService;
    private MeasureService measureService;

    private IngredientService ingredientService;

    private UserdataService userdataService;

    @Autowired
    public MealController(MealService mealService, CategoryService categoryService, MeasureService measureService, IngredientService ingredientService, UserdataService userdataService){
        this.mealService = mealService;
        this.categoryService = categoryService;
        this.measureService = measureService;
        this.ingredientService = ingredientService;
        this.userdataService = userdataService;
    }


    @GetMapping("/list")
    public String list(@PathVariable String username, Model theModel, Principal principal){
        if (!username.equals(principal.getName())) {
            return "redirect:/access-denied";
        }

        Userdata userdata = userdataService.findUserdataByUsername(username);

        theModel.addAttribute("username", username);
        List<Meal> theMeals = mealService.findAllMealsWithCategory();
        theModel.addAttribute("meals", theMeals);
        return "meals/main-meal-planner";
    }

    @GetMapping("/add-meal")
    public String addMeal(Model theModel) {
        return prepareModelForMealForm(new Meal(), theModel);
    }

    @GetMapping("/update-meal")
    public String updateMeal(@RequestParam("mealId") Integer mealId, Model theModel){
        Meal meal = mealService.findMealWithAllInfoById(mealId);
        return prepareModelForMealForm(meal, theModel);

    }

    private String prepareModelForMealForm(Meal meal, Model theModel) {
        if (meal.getMealDescription() == null) {
            meal.setMealDescription(new MealDescription());
        }
        theModel.addAttribute("meal", meal);
        theModel.addAttribute("categories", categoryService.findAll());
        theModel.addAttribute("measures", measureService.findAll());
        if (meal.getId() == null){
            return "meals/add-meal-form";
        } else {
            return "meals/update-meal-form";
        }
    }

    @PostMapping("/save")
    public String saveMeal(@ModelAttribute Meal meal,
                           @RequestParam(value = "categoryId", required = false) String categoryId) {
        processMeal(meal, categoryId);
        mealService.save(meal);
        return "redirect:/meals/list";
    }

    private void processMeal(Meal meal, String categoryId) {
        if (categoryId != null) {
            Category category = categoryService.findById(Integer.parseInt(categoryId));
            meal.setCategory(category);
        }

        List<Ingredient> ingredients = meal.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getMeasure() != null && ingredient.getMeasure().getId() != null) {
                    Measure measure = measureService.findById(ingredient.getMeasure().getId());
                    ingredient.setMeasure(measure);
                }
            }
        } else {
            logger.warn("No ingredients provided in the meal");
        }

        if (meal.getId() != null) {
            ingredientService.deleteByMealId(meal.getId());
        }
    }

    @GetMapping("/show-meal")
    public String showMeal(@RequestParam("mealId") Integer mealId, Model theModel) {
        Meal meal = mealService.findMealWithAllInfoById(mealId);
        if (meal != null) {
            theModel.addAttribute("meal", meal);
            return "meals/show-meal";
        } else {
            return "redirect:/meals/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("mealId") int theId){
        mealService.deleteById(theId);
        return "redirect:/meals/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}