package com.mealplanner.service;

import com.mealplanner.entity.Measure;

import java.util.List;

public interface MeasureService {
    List<Measure> findAll();
    Measure findById(Integer id);
    Measure save(Measure category);
    void deleteById(Integer id);
}
