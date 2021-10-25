package com.example.Football.Controller;


import com.example.Football.Model.DivisionModel;
import com.example.Football.Model.TeamModel;
import com.example.Football.Repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    public DivisionRepository divisionRepository;

    @GetMapping("/all")
    public List<DivisionModel> getDivision() {
        return divisionRepository.findAll();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createDivision(@RequestBody DivisionModel division) {
        try {
            boolean divisionExist = divisionRepository.DivisionExists(division.getName());
            DivisionModel divisionOptional = null;

            if (divisionExist)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Division "+ division.getName() +" already exists!");
            }
            if(divisionOptional !=null)
            {
                Optional<DivisionModel> division1 = divisionRepository.findById(division.getId());
                division1 .get().setName(division1.get().getName());
                division1.get().setStatus('T');
                return ResponseEntity.status(HttpStatus.FOUND).body("The division is already exist but it has been reactivated");
            }

            this.divisionRepository.save(division);
            return ResponseEntity.status(HttpStatus.FOUND).body("Division "+ division.getName() +" Created successfully!");


        } catch (Exception e) {
            throw e;
        }

    }


    //update

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateDivision(@RequestBody DivisionModel division ,@PathVariable int id)throws Exception {
        boolean existingDivision = divisionRepository.DivisionExists(id);
        if (!existingDivision) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Division does not exist!");
        }
        DivisionModel divisionModel = divisionRepository.findById(id).get();
        divisionModel.setName(division.getName());
        divisionModel.setStatus('T');

        divisionRepository.save(divisionModel);

        return ResponseEntity.ok(division.getName() + " Successfully Updated!");
    }




    //delete
    @DeleteMapping  ("/delete/{id}")
    public ResponseEntity<String> deleteDivision(@PathVariable int id)throws Exception{
        Optional<DivisionModel> existingDivision = divisionRepository.findById(id);
        if(existingDivision.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Division Not Found!");
        }
        divisionRepository.deleteById(id);
        return ResponseEntity.ok("Successfully Deleted " + existingDivision.get().getName() + "!");
    }


}


