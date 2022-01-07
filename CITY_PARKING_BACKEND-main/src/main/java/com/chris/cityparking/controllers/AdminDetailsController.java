package com.chris.cityparking.controllers;

import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.services.AdminDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/apiv1/admin")
public class AdminDetailsController {
    @Autowired
    AdminDetailService adminDetailService;

    @PostMapping("/save")
    public ResponseEntity<AdminDetails> createAdmin(@RequestBody AdminDetails adminDetails){
        adminDetailService.createAdminDetail(adminDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminDetails adminDetails){
        adminDetailService.updateAdminDetail(adminDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{adminID}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminID){
        adminDetailService.deleteAdminDetail(adminID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
