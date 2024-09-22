package com.mealplanner.service;

import com.mealplanner.dao.UserdataRepository;
import com.mealplanner.entity.Userdata;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserdataServiceImpl implements UserdataService{

    private UserdataRepository userdataRepository;

    @Autowired
    public UserdataServiceImpl(UserdataRepository userdataRepository){
        this.userdataRepository = userdataRepository;
    }

    @Override
    public List<Userdata> findAll() {
        return null;
    }

    @Override
    public Userdata findById(Integer id) {
        return null;
    }

    @Override
    public Userdata save(Userdata userdata) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Userdata findUserdataByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        return userdataRepository.findUserdataByUsername(username);

    }
}
