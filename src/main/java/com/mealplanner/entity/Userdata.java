package com.mealplanner.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userdata")
public class Userdata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userdata",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Meal> meals;

    public Userdata() {
    }

    public Userdata(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Meal> getMeals() {
        if (meals == null){
            meals = new ArrayList<>();
        }
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal meal){
        getMeals().add(meal);
        meal.setUserdata(this);
    }

    @Override
    public String toString() {
        return "Userdata{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
