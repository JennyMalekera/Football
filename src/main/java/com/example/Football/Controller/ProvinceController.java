package com.example.Football.Controller;



import com.example.Football.Model.ProvinceModel;
import com.example.Football.Model.TeamModel;
import com.example.Football.Repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    public ProvinceRepository provinceRepository;


    @GetMapping("/all")
    public List<ProvinceModel> getProvince() {
        return provinceRepository.findAll();
    }


    @PostMapping("/create")

    public ResponseEntity<String> createProvince(@Valid @RequestBody ProvinceModel province ) {
        try {
            boolean provinceExist = provinceRepository.ProvinceExists(province.getPname());
            //boolean provinceExist = false;
            ProvinceModel provinceOptional = null;

            if (provinceExist)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Province "+ province.getPname() +" already exists!");
            }
            if(provinceOptional !=null)
            {
                Optional<ProvinceModel> province1 = provinceRepository.findById(province.getId());
                province1.get().setPname(province1.get().getPname());
                province1.get().setStatus('T');
                return ResponseEntity.status(HttpStatus.FOUND).body("The province is already exist but it has been reactivated");
            }

            this.provinceRepository.save(province);
            return ResponseEntity.status(HttpStatus.FOUND).body("Province "+ province.getPname() +" Created successfully!");


        } catch (Exception e) {
            throw e;
        }

    }


    // update
    @PutMapping ("/edit/{id}")
    public ResponseEntity<String> updateProvince(@Valid @RequestBody ProvinceModel province , @PathVariable int id)throws Exception {
        boolean existingProvince = provinceRepository.ProvinceExists(id);
       // boolean existingProvince = false;
        if (!existingProvince) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province does not exist!");
        }
        ProvinceModel provinceModel = provinceRepository.findById(id).get();
        provinceModel.setPname(province.getPname());
        provinceModel.setStatus('T');

        provinceRepository.save(provinceModel);

        return ResponseEntity.ok(province.getPname() + " Successfully Updated!");
    }




    //delete
    @DeleteMapping  ("/delete/{id}")
    public ResponseEntity<String> deleteProvince(@PathVariable int id)throws Exception{
        Optional<ProvinceModel> existingProvince = provinceRepository.findById(id);
        if(existingProvince.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province Not Found!");
        }
        provinceRepository.deleteById(id);
        return ResponseEntity.ok("Successfully Deleted " + existingProvince.get().getPname() + "!");
    }


}



