package com.mealplanner.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "measure")
public class Measure {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "measure")
    private String measure;

    @OneToMany(mappedBy = "measure",
                fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}                )
    private List<Ingredient> ingredients;

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", measure='" + measure +
                '}';
    }
}
