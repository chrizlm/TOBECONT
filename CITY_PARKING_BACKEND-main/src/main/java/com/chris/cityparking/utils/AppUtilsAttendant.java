package com.chris.cityparking.utils;

import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUtilsAttendant {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    public void dealWithAppUser(AttendantDetails attendantDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(attendantDetails.getFirstName());
        appUser.setEmail(attendantDetails.getEmail());
        appUser.setPassword(attendantDetails.getPassword());

        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(attendantDetails.getEmail(), "ROLE_ATTENDANT");
    }

    public void dealWithAppUserUpdate(AttendantDetails attendantDetails){
        AppUser user = appUserRepo.getByEmail(attendantDetails.getEmail());
        user.setFirstName(attendantDetails.getFirstName());
        user.setEmail(attendantDetails.getEmail());
        user.setPassword(attendantDetails.getPassword());
        appUserRepo.save(user);
    }
}
