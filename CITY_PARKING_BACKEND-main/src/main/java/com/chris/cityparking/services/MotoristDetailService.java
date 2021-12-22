package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.MotoristNotFoundException;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.repositories.MotoristDetailsRepo;
import com.chris.cityparking.utils.AppUtilsMotorist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristDetailService {
    @Autowired
    MotoristDetailsRepo motoristDetailsRepo;


    @Autowired
    AppUtilsMotorist appUtilsMotorist;


    /*
    getting details
    saving details
    update details
    deleting details
     */

    public void createMotoristDetail(MotoristDetails motoristDetails){
        motoristDetailsRepo.save(motoristDetails);
        appUtilsMotorist.dealWithAppUser(motoristDetails);
    }

    public MotoristDetails getMotorist(Long motoristID){
        return motoristDetailsRepo.getById(motoristID);
    }

    public List<MotoristDetails> getAllMotorists(){
        return motoristDetailsRepo.findAll();
    }

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

    public void deleteMotorist(Long motoristID){
        motoristDetailsRepo.deleteById(motoristID);
    }
}
