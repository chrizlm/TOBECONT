package com.chris.cityparking.controllers;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.services.MotoristDetailService;
import com.chris.cityparking.services.MotoristDetailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/apiv1/motorist")
public class MotoristDetailsController {
    @Autowired
    MotoristDetailService motoristDetailService;

    @PostMapping("/save")
    public ResponseEntity<MotoristDetails> createMotorist(@RequestBody MotoristDetails motoristDetails){
        motoristDetailService.createMotoristDetail(motoristDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getDetails/{motoristID}")
    public ResponseEntity<List<MotoristDetails>> getMotorist(@PathVariable Long motoristID){
        return new ResponseEntity<>(motoristDetailService.getMotorist(motoristID), HttpStatus.OK);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<MotoristDetails> getMotoristInfo(@PathVariable String email){
        return new ResponseEntity<>(motoristDetailService.getMotoristDetails(email), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<MotoristDetails>> getAllMotorists(){
        return new ResponseEntity<>(motoristDetailService.getAllMotorists(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMotorist(@RequestBody MotoristDetails motoristDetails){
        motoristDetailService.updateMotorist(motoristDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateMotoristPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        motoristDetailService.updateMotoristDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{motoristID}")
    public ResponseEntity<?> deleteMotorist(@PathVariable Long motoristID){
        motoristDetailService.deleteMotorist(motoristID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
