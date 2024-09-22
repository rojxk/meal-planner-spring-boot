package com.mealplanner.controller;

import com.mealplanner.annotation.CheckUsername;
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
    @CheckUsername
    public String list(@PathVariable String username, Model theModel){

        Userdata userdata = userdataService.findUserdataByUsername(username);
        List<Meal> theMeals = mealService.findAllWithCategoryByUserId(userdata.getId());
        theModel.addAttribute("meals", theMeals);
        theModel.addAttribute("username", username);
        return "meals/main-meal-planner";
    }

    @GetMapping("/add-meal")
    @CheckUsername
    public String addMeal(@PathVariable String username, Model theModel) {

        return prepareModelForMealForm(new Meal(), theModel, username);
    }

    @GetMapping("/update-meal")
    @CheckUsername
    public String updateMeal(@PathVariable String username, @RequestParam("mealId") Integer mealId, Model theModel){
        Meal meal = mealService.findMealWithAllInfoById(mealId);
        return prepareModelForMealForm(meal, theModel, username);

    }

    private String prepareModelForMealForm(Meal meal, Model theModel, String username) {
        if (meal.getMealDescription() == null) {
            meal.setMealDescription(new MealDescription());
        }
        theModel.addAttribute("meal", meal);
        theModel.addAttribute("categories", categoryService.findAll());
        theModel.addAttribute("measures", measureService.findAll());
        theModel.addAttribute("username", username);
        if (meal.getId() == null){
            return "meals/add-meal-form";
        } else {
            return "meals/update-meal-form";
        }
    }

    @PostMapping("/save")
    @CheckUsername
    public String saveMeal(@PathVariable String username,
                           @ModelAttribute Meal meal,
                           @RequestParam(value = "categoryId", required = false) String categoryId) {
        processMeal(meal, categoryId, username);
        mealService.save(meal);
        return "redirect:/" + username + "/meals/list";
    }

    private void processMeal(Meal meal, String categoryId, String username) {

        if(username != null){
            Userdata userdata = userdataService.findUserdataByUsername(username);
            meal.setUserdata(userdata);

        }

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
    @CheckUsername
    public String showMeal(@PathVariable String username,
                           @RequestParam("mealId") Integer mealId, Model theModel) {
        Meal meal = mealService.findMealWithAllInfoById(mealId);
        theModel.addAttribute("username", username);
        if (meal != null) {
            theModel.addAttribute("meal", meal);
            return "meals/show-meal";
        } else {
            return "redirect:/" + username + "/meals/list";
        }
    }

    @GetMapping("/delete")
    @CheckUsername
    public String delete(@PathVariable String username,
                         @RequestParam("mealId") int theId){
        mealService.deleteById(theId);
        return "redirect:/" + username + "/meals/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


}