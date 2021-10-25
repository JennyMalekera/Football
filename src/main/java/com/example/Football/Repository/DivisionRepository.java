package com.example.Football.Repository;


import com.example.Football.Model.DivisionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<DivisionModel, Integer> {


  //   Returns a boolean value based on a condition if a division exists (Do not use on Insert!!!!)
  @Query("select case when (count (d) > 0) then true else false end from DivisionModel d where d.id = ?1 and d.status = 'T'")
  boolean DivisionExists(int id);


  @Query("select case when (count (d) > 0) then true else false end from DivisionModel d where d.name = ?1 ")
  boolean DivisionExists(String name);


}