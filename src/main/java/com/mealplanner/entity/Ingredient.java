package com.mealplanner.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @OneToOne
    @JoinColumn(name = "measure_id")
    private Measure measure;

    public Ingredient(){

    };

    public Ingredient(String ingredient, BigDecimal quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredient='" + ingredient + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
