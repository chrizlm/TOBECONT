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

    @PostMapping("/checkSpace")
    public ResponseEntity<Integer> getSpacesForBooking(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingDetailService.getAvailableSpaceForBooking(parkingDetails), HttpStatus.OK);
    }

    @PostMapping("/save/{email}")
    public ResponseEntity<String> createParkingDetail(@RequestBody ParkingDetails parkingDetails, @PathVariable String email){
        //parkingDetailService.createParkingDetail(parkingDetails);
        //return new ResponseEntity<>(HttpStatus.CREATED);

        return new ResponseEntity<>(parkingDetailService.saveParkingDetail(parkingDetails, email), HttpStatus.CREATED);
    }

    @GetMapping("/get/{numberPlate}")
    public ResponseEntity<List<ParkingDetails>> getParkingDetails(@PathVariable String numberPlate){
        return new ResponseEntity<>(parkingDetailService.getParkingDetail(numberPlate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParkingDetails>> getAllParkingDetails(){
        return new ResponseEntity<>(parkingDetailService.getAllParkingDetails(), HttpStatus.OK);
    }

    /*
    @PutMapping("/update")
    public ResponseEntity<?> updateParkingDetail(@RequestBody ParkingDetails parkingDetails){
        parkingDetailService.updateParkingDetail(parkingDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     */
/*
    @DeleteMapping("/{numberPlate}")
    public ResponseEntity<?> deleteParkingDetail(@PathVariable String numberPlate){
        parkingDetailService.deleteParkingDetails(numberPlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

 */

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllParkingDetails(){
        parkingDetailService.deleteAllParkingDetails();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
