package com.mealplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "measure")
public class Measure {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "measure")
    private String measure;

    @OneToOne(mappedBy = "measure")
    private Ingredient ingredient;

    public Measure(){

    };

    public Measure(String measure) {
        this.measure = measure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", measure='" + measure + '\'' +
                ", ingredient=" + ingredient +
                '}';
    }
}
