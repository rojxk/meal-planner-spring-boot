package com.mealplanner.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "making_time")
    private int makingTime;

    @Column(name = "portions")
    private int portions;

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

    public int getMakingTime() {
        return makingTime;
    }

    public void setMakingTime(int makingTime) {
        this.makingTime = makingTime;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
