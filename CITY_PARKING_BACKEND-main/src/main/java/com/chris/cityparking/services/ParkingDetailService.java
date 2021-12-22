package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.ParkingDetailsNotFoundException;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingDetailService {
    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;
    @Autowired
    ParkingLotRepo parkingLotRepo;

    /*
    getting details
    saving details
    update details
    deleting details
     */

    public void createParkingDetail(ParkingDetails parkingDetails){

        /*
        an if statement to be added
        check if the carrent capacity of parking lot is full
        if free space available save else find another
         */
        ParkingLot parkingLot = parkingLotRepo.getByParkingLotName(parkingDetails.getParkingLotName());
        parkingDetails.setParkingLot(parkingLot);
        parkingDetailsRepo.save(parkingDetails);
    }

    public ParkingDetails getParkingDetail(String numberPlate){
        return parkingDetailsRepo.getByNumberPlate(numberPlate);
    }

    public List<ParkingDetails> getAllParkingDetails(){
        return parkingDetailsRepo.findAll();
    }

    public void updateParkingDetail(ParkingDetails parkingDetails){
        parkingDetailsRepo.findByNumberPlate(parkingDetails.getNumberPlate()).ifPresentOrElse(parking ->{
            parking.setVehicleType(parkingDetails.getVehicleType());
            parking.setLocation(parkingDetails.getLocation());
            parking.setParkingLotName(parkingDetails.getParkingLotName());
            parking.setParkTime(parkingDetails.getParkTime());
            parking.setParkingDate(parkingDetails.getParkingDate());
            parking.setParkDuration(parkingDetails.getParkDuration());
            parking.setParkingLot(parkingLotRepo.getByParkingLotName(parkingDetails.getParkingLotName()));
            parkingDetailsRepo.save(parking);
        }, ()->{
            throw new ParkingDetailsNotFoundException(parkingDetails.getNumberPlate());
        });
    }

    public void deleteParkingDetails(String numberPlate){
        parkingDetailsRepo.deleteByNumberPlate(numberPlate);
    }

    public void deleteAllParkingDetails(){
        parkingDetailsRepo.deleteAll();
    }
}
