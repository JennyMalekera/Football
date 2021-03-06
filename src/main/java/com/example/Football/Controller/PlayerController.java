package com.example.Football.Controller;

import com.example.Football.Model.DivisionModel;
import com.example.Football.Model.PlayerModel;

import com.example.Football.Model.TeamModel;
import com.example.Football.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    public PlayerRepository playerRepository;



    //get all player
    @GetMapping("/all")
    public List<PlayerModel> getPlayer() {
        return playerRepository.findAll();
    }

    @PostMapping("/create")

    public ResponseEntity<String> createPlayer(@RequestBody PlayerModel player ) {
        try {
            boolean playerExist = playerRepository.PlayerExists(player.getFirstname());
            PlayerModel playerOptional = null;

            if (playerExist)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player "+ player.getFirstname() +" already exists!");
            }
            if(playerOptional !=null)
            {
                Optional<PlayerModel> player1 = playerRepository.findById(player.getId());
                player1.get().setFirstname(player1.get().getFirstname());
                player1.get().setStatus('T');
                return ResponseEntity.status(HttpStatus.FOUND).body("The Player is already exist hence it has been reactivated");
            }

            this.playerRepository.save(player);
            return ResponseEntity.status(HttpStatus.FOUND).body("Player "+ player.getFirstname() +" Created successfully!");


        } catch (Exception e) {
            throw e;
        }

    }


    // update
    @PutMapping ("/edit/{id}")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerModel player ,@PathVariable int id)throws Exception {
        boolean existingPlayer = playerRepository.PlayerExists(id);
        if (!existingPlayer) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player does not exist!");
        }
        PlayerModel playerModel = playerRepository.findById(id).get();
        playerModel.setFirstname(player.getFirstname());
        playerModel.setLastname(player.getLastname());
        playerModel.setAge(player.getAge());
        playerModel.setEmail(player.getEmail());
        playerModel.setStatus('T');
        playerModel.setTeamId(player.getTeamId());

        playerRepository.save(playerModel);

        return ResponseEntity.ok(player.getFirstname() + " Successfully Updated!");
    }





    //delete
    @DeleteMapping  ("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id)throws Exception{
        Optional<PlayerModel> existingPlayer = playerRepository.findById(id);
        if(existingPlayer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found!");
        }
        playerRepository.deleteById(id);
        return ResponseEntity.ok("Successfully Deleted " + existingPlayer.get().getFirstname() + "!");
    }


}

