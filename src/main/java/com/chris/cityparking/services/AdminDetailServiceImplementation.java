package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.AdminNotFoundException;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.repositories.AdminDetailsRepo;
import com.chris.cityparking.utils.AppUtilsAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AdminDetailServiceImplementation implements AdminDetailService{
   @Autowired
    AdminDetailsRepo adminDetailsRepo;
   @Autowired
   AppUtilsAdmin appUtilsAdmin;

    @Override
    public void createAdminDetail(AdminDetails adminDetails) {
        adminDetailsRepo.save(adminDetails);
        appUtilsAdmin.dealWithAppUser(adminDetails);
    }

    @Override
    public void updateAdminDetail(AdminDetails adminDetails) {
        adminDetailsRepo.findById(adminDetails.getAdminID()).ifPresentOrElse(admin ->{
            admin.setFirstName(adminDetails.getFirstName());
            admin.setLastName(adminDetails.getLastName());
            admin.setEmail(adminDetails.getEmail());
            admin.setGender(adminDetails.getGender());
            admin.setPassword(adminDetails.getPassword());
            adminDetailsRepo.save(admin);
        }, () ->{
            throw new AdminNotFoundException(adminDetails.getAdminID());
        });
    }

    @Override
    public void deleteAdminDetail(Long adminID) {
        adminDetailsRepo.deleteById(adminID);
    }
}
