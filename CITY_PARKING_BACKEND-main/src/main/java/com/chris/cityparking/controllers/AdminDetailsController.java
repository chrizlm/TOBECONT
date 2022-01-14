package com.chris.cityparking.controllers;

import com.chris.cityparking.controllers.Forms.EmailForm;
import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.services.AdminDetailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
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

    @GetMapping("/get/{email}")
    public ResponseEntity<AdminDetails> getAdminInfo(@PathVariable String email){
        return new ResponseEntity<>(adminDetailService.getAdminInfo(email),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminDetails adminDetails){
        adminDetailService.updateAdminDetail(adminDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateAdminPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        adminDetailService.updateAdminDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{adminID}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminID){
        adminDetailService.deleteAdminDetail(adminID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

