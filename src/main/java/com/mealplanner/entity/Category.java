package com.mealplanner.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Meal> meals;

    public Category(){

    }

    public Category(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Meal> getMeal() {
        return meals;
    }

    public void setMeal(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }

    public void addMeal(Meal meal) {
        if (meals == null){
            meals = new ArrayList<>();
        }
        meals.add(meal);
        meal.setCategory(this);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
        meal.setCategory(null);
    }

}
