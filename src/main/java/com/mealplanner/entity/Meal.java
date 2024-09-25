package com.mealplanner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meal")
public class Meal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message="Meal name is required")
    @Size(min=1, message="Meal name is required")
    @Size(max = 80, message="Too long name")
    @Column(name = "meal_name")
    private String mealName;


    @Min(value = 1, message = "Making time must be at least 1 minute")
    @Max(value = 9999, message = "Making time must not exceed 9999 minutes")
    @Column(name = "making_time")
    private Integer makingTime;


    @NotNull(message = "Serving size is required")
    @Min(value = 1, message = "Portions must be at least 1")
    @Max(value = 9999, message = "Portions must not exceed 9999")
    @Column(name = "portions")
    private Integer portions;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private MealDescription mealDescription;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userdata userdata;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "meal",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;


    public Meal(){

    };

    public Meal(String mealName, int makingTime, int portions) {
        this.mealName = mealName;
        this.makingTime = makingTime;
        this.portions = portions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getMakingTime() {
        return makingTime;
    }

    public void setMakingTime(Integer makingTime) {
        this.makingTime = makingTime;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public MealDescription getMealDescription() {
        if (this.mealDescription == null) {
            this.mealDescription = new MealDescription();
        }
        return this.mealDescription;
    }

    public void setMealDescription(MealDescription mealDescription) {
        this.mealDescription = mealDescription;
        if (mealDescription != null) {
            mealDescription.setMeal(this);
        }
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        } else {
            this.ingredients.clear();
        }
        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }
    }

    public List<Ingredient> getIngredients() {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        return this.ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        getIngredients().add(ingredient);
        ingredient.setMeal(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        getIngredients().remove(ingredient);
        ingredient.setMeal(null);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", mealName='" + mealName + '\'' +
                ", makingTime=" + makingTime +
                ", portions=" + portions +
                '}';
    }


}
