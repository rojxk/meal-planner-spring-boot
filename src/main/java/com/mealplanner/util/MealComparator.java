package com.mealplanner.util;

import com.mealplanner.entity.Meal;

import java.util.Comparator;

public class MealComparator implements Comparator<Meal> {

    private final MealSortCriteria criteria;

    public MealComparator(MealSortCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public int compare(Meal m1, Meal m2) {
        return switch (criteria) {
            case DEFAULT -> Integer.compare(m1.getId(), m2.getId());
            case NEWEST -> Integer.compare(m2.getId(), m1.getId());
            case NAME_AZ -> m1.getMealName().compareToIgnoreCase(m2.getMealName());
            case NAME_ZA -> m2.getMealName().compareToIgnoreCase(m1.getMealName());
            case CATEGORY -> Integer.compare(m1.getCategory().getId(), m2.getCategory().getId());
            case MAKING_TIME -> Integer.compare(m1.getMakingTime(), m2.getMakingTime());
            case PORTIONS -> Integer.compare(m1.getPortions(), m2.getPortions());
        };

    }
}
