package com.mealplanner.dao;

import com.mealplanner.entity.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdataRepository extends JpaRepository<Userdata, Integer> {

    @Query("SELECT u FROM Userdata u WHERE u.username=:username")
    Userdata findUserdataByUsername(String username);

}
