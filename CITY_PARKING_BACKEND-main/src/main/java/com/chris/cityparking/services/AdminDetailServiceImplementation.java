package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.exceptions.AdminNotFoundException;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.repositories.AdminDetailsRepo;
import com.chris.cityparking.utils.AppUtilsAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AdminDetailServiceImplementation implements AdminDetailService{
    private final PasswordEncoder passwordEncoder;
   @Autowired
    AdminDetailsRepo adminDetailsRepo;
   @Autowired
   AppUtilsAdmin appUtilsAdmin;
   @Autowired
   AppUserService appUserService;

    @Override
    public void createAdminDetail(AdminDetails adminDetails) {
        //adminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        adminDetailsRepo.save(adminDetails);
        appUtilsAdmin.dealWithAppUser(adminDetails);
    }

    @Override
    public void updateAdminDetailNew(AdminDetails adminDetails){
        AdminDetails newAdminDetails = getAdminInfo(adminDetails.getEmail());
        newAdminDetails.setFirstName(adminDetails.getFirstName());
        newAdminDetails.setLastName(adminDetails.getLastName());
        newAdminDetails.setEmail(adminDetails.getEmail());
        newAdminDetails.setGender(adminDetails.getGender());
        newAdminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        adminDetailsRepo.save(newAdminDetails);
    }

    @Override
    public void updateAdminDetailPassword(EmailPasswordForm emailPasswordForm){
        AdminDetails newAdminInfo = getAdminInfo(emailPasswordForm.getEmail());
        newAdminInfo.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        adminDetailsRepo.save(newAdminInfo);
        appUserService.changeUserPassword(emailPasswordForm);
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

    @Override
    public AdminDetails getAdminInfo(String email) {
        return adminDetailsRepo.getByEmail(email);
    }
}
