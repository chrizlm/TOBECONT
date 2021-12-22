package com.chris.cityparking.controllers;

import com.chris.cityparking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/apiv1/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/freeSpace/{parkingLotName}")
    public int getFreeSpaces(@PathVariable String parkingLotName){
        return bookingService.getAvailableSpace(parkingLotName);
    }

    @GetMapping("/occupiedSpaces/{parkingLotName}")
    public int getOccupiedSpaces(@PathVariable String parkingLotName){
        return bookingService.getOccupiedSpaces(parkingLotName);
    }


    @GetMapping("/locdata")
    public ResponseEntity<Set<String>> getLocationData(){
        return new ResponseEntity<>(bookingService.getAreas(), HttpStatus.OK);
    }
    /*public List<String> getLocationData(){
        return bookingService.getAreas();
    }
     */


    @GetMapping(value = "/parkingdata/{locationName}", produces = "application/json")
    public ResponseEntity<List<String>> getParkingData(@PathVariable String locationName){
        return new ResponseEntity<>(bookingService.getParkings(locationName), HttpStatus.OK);
    }
    /*
    public List<String> getParkingData(@PathVariable String locationName){
        return bookingService.getParkings(locationName);
    }

     */
}

