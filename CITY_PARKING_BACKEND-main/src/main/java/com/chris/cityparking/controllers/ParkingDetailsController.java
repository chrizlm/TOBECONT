package com.chris.cityparking.controllers;


import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.services.ParkingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@Controller
@RequestMapping("/apiv1/parkingdetails")
public class ParkingDetailsController {
    @Autowired
    ParkingDetailService parkingDetailService;

    @PostMapping("/save")
    public ResponseEntity<ParkingDetails> createParkingDetail(@RequestBody ParkingDetails parkingDetails){
        parkingDetailService.createParkingDetail(parkingDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/{numberPlate}")
    public ResponseEntity<ParkingDetails> getParkingDetails(@PathVariable String numberPlate){
        return new ResponseEntity<>(parkingDetailService.getParkingDetail(numberPlate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParkingDetails>> getAllParkingDetails(){
        return new ResponseEntity<>(parkingDetailService.getAllParkingDetails(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateParkingDetail(@RequestBody ParkingDetails parkingDetails){
        parkingDetailService.updateParkingDetail(parkingDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{numberPlate}")
    public ResponseEntity<?> deleteParkingDetail(@PathVariable String numberPlate){
        parkingDetailService.deleteParkingDetails(numberPlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllParkingDetails(){
        parkingDetailService.deleteAllParkingDetails();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
