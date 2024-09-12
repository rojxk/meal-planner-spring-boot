package com.mealplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meal_description")
public class MealDescription {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "meal_description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "mealDescription", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Meal meal;

    public MealDescription(){

    }

    public MealDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "MealDescription{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", meal=" + meal +
                '}';
    }
}
