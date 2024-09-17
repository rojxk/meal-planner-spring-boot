package com.mealplanner.service;

import com.mealplanner.dao.MeasureRepository;
import com.mealplanner.entity.Measure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureServiceImpl implements MeasureService{

    private MeasureRepository measureRepository;

    @Autowired
    public MeasureServiceImpl(MeasureRepository measureRepository){
        this.measureRepository = measureRepository;
    }
    @Override
    public List<Measure> findAll() {
        return measureRepository.findAll();
    }

    @Override
    public Measure findById(Integer id){return null;
    }

    @Override
    public Measure save(Measure category) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
