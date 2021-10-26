package com.example.Football.Repository;



import com.example.Football.Model.ProvinceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceModel, Integer> {

//    //Returns a boolean value based on a condition if a provinceModel exists (Do not use on Insert!!!!)
  @Query("select case when (count (p) > 0) then true else false end from ProvinceModel p where p.id = ?1 and p.status = 'T'")
    boolean ProvinceExists(int id);

//  @Query("select case when (count (p) > 0) then true else false end from ProvinceModel p where t.id = ?1 ")
//  boolean ProvinceExist(int id);

    @Query("select case when (count (p) > 0) then true else false end from ProvinceModel p where p.pname = ?1 ")
    boolean ProvinceExists(String pname);



}


