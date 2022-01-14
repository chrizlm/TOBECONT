package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Configuration
@EnableScheduling
@EnableAsync
public class AppUtilsParkingDetails {
    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();

    //@DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    Date todaysTime = Calendar.getInstance().getTime();


    @Async
    @Scheduled(cron = "0 0 * * * *")
    public void removeRecord(){

        List<ParkingDetails> recordList = parkingDetailsRepo.getByParkingDate(todaysDate);

        for(ParkingDetails parkingDetails : recordList){
            if(parkingDetails.getExpiryParkTime().compareTo(todaysTime) > 0){
                parkingDetailsRepo.delete(parkingDetails);
            }
        }
    }


}
