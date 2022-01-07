package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.ParkingDetailsNotFoundException;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingDetailServiceImplementation implements ParkingDetailService{
    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;
    @Autowired
    ParkingLotRepo parkingLotRepo;
    @Autowired
    BookingService bookingService;

    /*
    getting details
    saving details
    update details
    deleting details
     */

    @Override
    public void createParkingDetail(ParkingDetails parkingDetails){

        /*
        an if statement to be added
        check if the carrent capacity of parking lot is full
        if free space available save else find another
         */
        ParkingLot parkingLot = parkingLotRepo.getByParkingLotName(parkingDetails.getParkingLotName());
        //pass date from details
        //LocalDate selectedDate = (parkingDetails.getParkingDate()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date selectedDate = parkingDetails.getParkingDate();
        bookingService.updateFreeSpace(parkingLot, selectedDate);
        //bookingService.updateFreeSpace(parkingLot);
        parkingDetails.setParkingLot(parkingLot);
        parkingDetailsRepo.save(parkingDetails);
    }

    @Override
    public List<ParkingDetails> getParkingDetail(String numberPlate){
        return parkingDetailsRepo.getByNumberPlate(numberPlate);
    }

    @Override
    public List<ParkingDetails> getAllParkingDetails(){
        return parkingDetailsRepo.findAll();
    }

    @Override
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

    @Override
    public void deleteParkingDetails(String numberPlate){
        parkingDetailsRepo.deleteByNumberPlate(numberPlate);
    }

    @Override
    public void deleteAllParkingDetails(){
        parkingDetailsRepo.deleteAll();
    }
}
