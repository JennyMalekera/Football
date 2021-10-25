package com.example.Football.Repository;

import com.example.Football.Model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.*;


import com.example.Football.Model.PlayerModel;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Integer> {


    //Returns a boolean value based on a condition if a playerModel exists (Do not use on Insert!!!!)
    @Query("select case when (count (p) > 0) then true else false end from PlayerModel p where p.id = ?1 and p.status = 'T'")
    boolean PlayerExists(int id);

//    @Query("select case when (count (p) > 0) then true else false end from PlayerModel p where t.id = ?1 ")
//    boolean PlayerExist(int id);

    @Query("select case when (count (p) > 0) then true else false end from PlayerModel p where p.firstname = ?1 ")
    boolean PlayerExists(String firstname);


}
