package com.chris.cityparking.utils;

import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUtilsAdmin {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    public void dealWithAppUser(AdminDetails adminDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(adminDetails.getFirstName());
        appUser.setEmail(adminDetails.getEmail());
        appUser.setPassword(adminDetails.getPassword());

        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(adminDetails.getEmail(), "ROLE_ADMIN");

    }

    public void dealWithAppUserUpdate(AdminDetails adminDetails){
        AppUser user = appUserRepo.getByEmail(adminDetails.getEmail());
        user.setFirstName(adminDetails.getFirstName());
        user.setEmail(adminDetails.getEmail());
        user.setPassword(adminDetails.getPassword());
        appUserRepo.save(user);
    }
}
