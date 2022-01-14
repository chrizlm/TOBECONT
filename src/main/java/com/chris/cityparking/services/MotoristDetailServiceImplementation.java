package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.exceptions.MotoristNotFoundException;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.repositories.MotoristDetailsRepo;
import com.chris.cityparking.utils.AppUtilsMotorist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class MotoristDetailServiceImplementation implements MotoristDetailService{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    MotoristDetailsRepo motoristDetailsRepo;


    @Autowired
    AppUtilsMotorist appUtilsMotorist;

    @Autowired
    AppUserService appUserService;


    /*
    getting details
    saving details
    update details
    deleting details
     */

    @Override
    public void createMotoristDetail(MotoristDetails motoristDetails){
        motoristDetailsRepo.save(motoristDetails);
        appUtilsMotorist.dealWithAppUser(motoristDetails);
    }
    @Override
    public List<MotoristDetails> getMotorist(Long motoristID){
        return motoristDetailsRepo.getByMotoristID(motoristID);
    }

    @Override
    public List<MotoristDetails> getAllMotorists(){
        return motoristDetailsRepo.findAll();
    }

    @Override
    public void updateMotorist(MotoristDetails motoristDetails){
        motoristDetailsRepo.findById(motoristDetails.getMotoristID()).ifPresentOrElse(motorist ->{
            motorist.setFirstName(motoristDetails.getFirstName());
            motorist.setLastName(motoristDetails.getLastName());
            motorist.setEmail(motoristDetails.getEmail());
            motorist.setGender(motoristDetails.getGender());
            motorist.setPassword(motoristDetails.getPassword());
            motoristDetailsRepo.save(motorist);
        }, () ->{
            throw new MotoristNotFoundException(motoristDetails.getMotoristID());
        });
    }

    @Override
    public void updateMotoristDetailPassword(EmailPasswordForm emailPasswordForm){
        MotoristDetails newMotoristInfo = getMotoristDetails(emailPasswordForm.getEmail());
        newMotoristInfo.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        motoristDetailsRepo.save(newMotoristInfo);
        appUserService.changeUserPassword(emailPasswordForm);
    }

    @Override
    public void deleteMotorist(Long motoristID){
        motoristDetailsRepo.deleteById(motoristID);
    }

    @Override
    public MotoristDetails getMotoristDetails(String email) {
        return motoristDetailsRepo.getByEmail(email);
    }
}
