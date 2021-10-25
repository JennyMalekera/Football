package com.example.Football.Controller;

import com.example.Football.Model.PlayerModel;
import com.example.Football.Model.TeamModel;
import com.example.Football.Repository.DivisionRepository;
import com.example.Football.Repository.ProvinceRepository;
import com.example.Football.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    public TeamRepository teamRepository;
    @Autowired
    ProvinceRepository provinceRepository ;
    @Autowired
    DivisionRepository divisionRepository;


    @GetMapping("/all")
    public List<TeamModel> getTeam() {
        return teamRepository.findAll();
    }


    @PostMapping("/create")

    public ResponseEntity<String> createTeam(@RequestBody TeamModel team ) {
        try {
            boolean teamExist = teamRepository.TeamExists(team.getTname());
            TeamModel teamOptional = null;

            if (teamExist)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team "+ team.getTname() +" already exists!");
            }
            if(teamOptional !=null)
            {
                Optional<TeamModel> team1 = teamRepository.findById(team.getId());
                team1.get().setTname(team1.get().getTname());
                team1.get().setStatus('T');
                return ResponseEntity.status(HttpStatus.FOUND).body("The team is already exist hence it has been reactivated");
            }

            this.teamRepository.save(team);
            return ResponseEntity.status(HttpStatus.FOUND).body("Team "+ team.getTname() +" Created successfully!");


        } catch (Exception e) {
            throw e;
        }

    }


  // update
    @PutMapping ("/edit/{id}")

    public ResponseEntity<String> updateTeam(@RequestBody TeamModel team ,@PathVariable int id)throws Exception {
        boolean existingTeam = teamRepository.TeamExists(id);
        if (!existingTeam) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team does not exist!");
        }
        TeamModel teamModel = teamRepository.findById(id).get();
        teamModel.setTname(team.getTname());
        teamModel.setStatus('T');
        teamModel.setProvinceId(team.getProvinceId());
        teamModel.setDivisionId(team.getDivisionId());
        //String name = team.getTname();

        //team.setStatus('T');
        teamRepository.save(teamModel);

        return ResponseEntity.ok(team.getTname() + " Successfully Updated!");
    }



    //delete
    @DeleteMapping  ("/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id)throws Exception{
        Optional<TeamModel> existingTeam = teamRepository.findById(id);
        if(existingTeam.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team Not Found!");
        }
        teamRepository.deleteById(id);
        return ResponseEntity.ok("Successfully Deleted " + existingTeam.get().getTname() + "!");
    }


}


