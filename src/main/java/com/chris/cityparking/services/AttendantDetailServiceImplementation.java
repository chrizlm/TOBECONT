package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.AttendantNotFoundException;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.repositories.AttendantDetailsRepo;
import com.chris.cityparking.utils.AppUtilsAttendant;
import com.chris.cityparking.utils.AppUtilsMotorist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AttendantDetailServiceImplementation implements AttendantDetailService{
    @Autowired
    AttendantDetailsRepo attendantDetailsRepo;
    @Autowired
    AppUtilsAttendant appUtilsAttendant;

    /*
    getting details
    saving details
    update details
    deleting details
     */


    @Override
    public void createAttendantDetail(AttendantDetails attendantDetails) {
        attendantDetailsRepo.save(attendantDetails);
        appUtilsAttendant.dealWithAppUser(attendantDetails);
    }

    @Override
    public List<AttendantDetails> getAttendant(Long attendantID) {
        return attendantDetailsRepo.getByAttendantID(attendantID);
    }

    @Override
    public List<AttendantDetails> getAllAttendants() {
        return attendantDetailsRepo.findAll();
    }

    @Override
    public void updateAttendant(AttendantDetails attendantDetails) {
        attendantDetailsRepo.findById(attendantDetails.getAttendantID()).ifPresentOrElse(attendant ->{
            attendant.setFirstName(attendantDetails.getFirstName());
            attendant.setLastName(attendantDetails.getLastName());
            attendant.setEmail(attendantDetails.getEmail());
            attendant.setGender(attendantDetails.getGender());
            attendant.setPassword(attendantDetails.getPassword());
            attendant.setAreaAssigned(attendantDetails.getAreaAssigned());
            attendantDetailsRepo.save(attendant);
        }, () ->{
                throw new AttendantNotFoundException(attendantDetails.getAttendantID());

        });
    }

    @Override
    public void deleteAttendant(Long attendantID) {
        attendantDetailsRepo.deleteById(attendantID);
    }
}
