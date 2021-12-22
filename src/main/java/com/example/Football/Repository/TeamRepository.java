package com.example.Football.Repository;



import com.example.Football.Model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Integer> {

    //Returns a boolean value based on a condition if a teamModel exists (Do not use on Insert!!!!)
   @Query("select case when (count (t) > 0) then true else false end from TeamModel t where t.id = ?1 and t.status = 'T'")
   boolean TeamExists(int id);


//   @Query("select case when (count (t) > 0) then true else false end from TeamModel t where t.id = ?1 ")
//   boolean TeamExist(int id);


    @Query("select case when (count (t) > 0) then true else false end from TeamModel t where t.tname = ?1 ")
    boolean TeamExists(String tname);

//Native query
//    @Query(
//            value = "SELECT * FROM TEAMS t WHERE t.status = 1",
//            nativeQuery = true)
//    Collection<Team> findAllActiveUsersNative();


}