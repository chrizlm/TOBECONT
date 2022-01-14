package com.chris.cityparking.controllers;


import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.services.AttendantDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/apiv1/attendant")
public class AttendantDetailsController {
    @Autowired
    AttendantDetailService attendantDetailService;

    @PostMapping("/save")
    public ResponseEntity<AttendantDetails> createAttendant(@RequestBody AttendantDetails attendantDetails){
        attendantDetailService.createAttendantDetail(attendantDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getDetails/{attendantID}")
    public ResponseEntity<List<AttendantDetails>> getAttendant(@PathVariable Long attendantID){
        return new ResponseEntity<>(attendantDetailService.getAttendant(attendantID), HttpStatus.OK);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<AttendantDetails> getAttendantInfo(@PathVariable String email){
        return new ResponseEntity<>(attendantDetailService.getAttendantDetails(email), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AttendantDetails>> getAllAttendants(){
        return new ResponseEntity<>(attendantDetailService.getAllAttendants(), HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateAttendantPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        attendantDetailService.updateAttendantDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttendant(@RequestBody AttendantDetails attendantDetails){
        attendantDetailService.updateAttendant(attendantDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{motoristID}")
    public ResponseEntity<?> deleteAttendant(@PathVariable Long attendantID){
        attendantDetailService.deleteAttendant(attendantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
