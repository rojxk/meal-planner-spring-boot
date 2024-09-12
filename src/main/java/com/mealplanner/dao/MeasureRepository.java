package com.mealplanner.dao;

import com.mealplanner.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure,Integer> {
}
