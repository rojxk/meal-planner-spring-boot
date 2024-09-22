package com.mealplanner.service;

import com.mealplanner.entity.Userdata;

import java.util.List;

public interface UserdataService {

    List<Userdata> findAll();
    Userdata findById(Integer id);
    Userdata save(Userdata userdata);
    void deleteById(Integer id);

    Userdata findUserdataByUsername(String username);

}
