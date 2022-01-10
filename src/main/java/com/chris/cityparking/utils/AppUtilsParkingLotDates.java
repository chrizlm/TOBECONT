package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.services.ParkingLotAndDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUtilsParkingLotDates {
    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;


    public void dealWithParkingLotAndDate(ParkingLot parkingLot){
        ParkingLotAndDates parkingLotAndDates = new ParkingLotAndDates();
        parkingLotAndDates.setParkingLotName(parkingLot.getParkingLotName());
        parkingLotAndDates.setParkingRegNo(parkingLot.getParkingRegNo());
        parkingLotAndDates.setTotalCapacity(parkingLot.getTotalParkingSpaces());
        //parkingLotAndDates.setAvailableSpace(parkingLot.getTotalParkingSpaces());
        parkingLotAndDates.setParkingLotLocation(parkingLot.getParkingLotLocation());
        parkingLotAndDates.setDate(parkingLot.getParkingLotDate());

        parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates);
        parkingLotAndDateService.addDateToParkingLot(parkingLot.getParkingLotName(), parkingLot.getParkingLotDate());
    }


}
