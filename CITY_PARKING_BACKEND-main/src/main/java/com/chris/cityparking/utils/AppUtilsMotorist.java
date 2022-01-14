package com.chris.cityparking.utils;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AppUtilsMotorist {
    @Autowired
    PasswordEncoder passwordEncoder;
     @Autowired
     AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    public void dealWithAppUser(MotoristDetails motoristDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(motoristDetails.getFirstName());
        appUser.setEmail(motoristDetails.getEmail());
        appUser.setPassword(motoristDetails.getPassword());

        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(motoristDetails.getEmail(), "ROLE_MOTORIST");
    }

    public void dealWithAppUserUpdate(MotoristDetails motoristDetails){
        AppUser user = appUserRepo.getByEmail(motoristDetails.getEmail());
        user.setFirstName(motoristDetails.getFirstName());
        user.setEmail(motoristDetails.getEmail());
        user.setPassword(motoristDetails.getPassword());
        appUserRepo.save(user);
    }
}
