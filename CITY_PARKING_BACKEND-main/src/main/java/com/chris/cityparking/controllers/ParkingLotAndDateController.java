package com.chris.cityparking.controllers;

import com.chris.cityparking.modules.BookingDates;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.services.ParkingLotAndDateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/apiv1/parkanddate")
public class ParkingLotAndDateController {
    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;

    @PostMapping("/save")
    public ResponseEntity<ParkingLotAndDates> saveParkingAndDates(@RequestBody ParkingLotAndDates parkingLotAndDates){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/apiv1/parkanddate/save").toUriString());
        return ResponseEntity.created(uri).body(parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates));
    }

    @PostMapping("/dates/save")
    public ResponseEntity<BookingDates> saveBookingDate(@RequestBody BookingDates bookingDates){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/apiv1/parkanddate/role/save").toUriString());
        return ResponseEntity.created(uri).body(parkingLotAndDateService.saveBookingDates(bookingDates));
    }

    @PostMapping("/dates/addtoparklot")
    public ResponseEntity<?> addDateToParkLot(@RequestBody DateToParkLotForm form){
        parkingLotAndDateService.addDateToParkingLot(form.getParkingLotName(), form.getDate());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByLocation/{location}")
    public ResponseEntity<List<ParkingLotAndDates>> getParkingLotByLocation(@PathVariable String location){
        return new ResponseEntity<>(parkingLotAndDateService.getListByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/get/{date}")
    public ResponseEntity<List<ParkingLotAndDates>> getParkingLotByDates(@PathVariable Date date){
        return new ResponseEntity<>(parkingLotAndDateService.getListByDates(date), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<ParkingLotAndDates> getAPK(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingLotAndDateService.getAParking(parkingDetails), HttpStatus.OK);
    }

    @GetMapping("/two")
    public ResponseEntity<ParkingLotAndDates> getAPKtwo(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingLotAndDateService.getAParkingTwo(parkingDetails), HttpStatus.OK);
    }
/*
    @GetMapping("/get/spaces")
    public ResponseEntity<List<ParkingLotAndDates>> getParkingLotByDates(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingLotAndDateService.getListByDates(date), HttpStatus.OK);
    }
*/
    @GetMapping("/all")
    public ResponseEntity<List<ParkingLotAndDates>> getAllParkingAndDates(){
        return new ResponseEntity<>(parkingLotAndDateService.getAllParkingLotAndDates(), HttpStatus.OK);
    }
}

@Data
class DateToParkLotForm{
    private String parkingLotName;
    private Date date;
}

@Data
class FindSpaceForm{
    private String parkingLotLocation;
    private String parkingLotName;
    private Date date;
}
