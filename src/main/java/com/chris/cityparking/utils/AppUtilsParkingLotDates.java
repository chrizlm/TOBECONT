package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.services.ParkingLotAndDateService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AppUtilsParkingLotDates {
    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();




    public void dealWithParkingLotAndDate(ParkingLot parkingLot) {
        int start = 0, end = 5;

        while(start <= end) {

            ParkingLotAndDates parkingLotAndDates = new ParkingLotAndDates();
            parkingLotAndDates.setParkingLotName(parkingLot.getParkingLotName());
            parkingLotAndDates.setRegNo(parkingLot.getParkingRegNo());
            //parkingLotAndDates.setParkingRegNo(parkingLot.getParkingRegNo());
            parkingLotAndDates.setTotalCapacity(parkingLot.getTotalParkingSpaces());
            //parkingLotAndDates.setAvailableSpace(parkingLot.getTotalParkingSpaces());
            parkingLotAndDates.setParkingLotLocation(parkingLot.getParkingLotLocation());


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(todaysDate);
            calendar.add(Calendar.DATE, start);

            parkingLotAndDates.setDate(calendar.getTime());

            parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates);

            start++;

        }

    }









        /*
        //Date expiryTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parkingDetails.getParkTime());
        calendar.add(Calendar.HOUR, parkingDetails.getParkDuration());
        //expiryTime = calendar.getTime();






        ParkingLotAndDates parkingLotAndDates = new ParkingLotAndDates();
        parkingLotAndDates.setParkingLotName(parkingLot.getParkingLotName());
        parkingLotAndDates.setParkingRegNo(parkingLot.getParkingRegNo());
        parkingLotAndDates.setTotalCapacity(parkingLot.getTotalParkingSpaces());
        //parkingLotAndDates.setAvailableSpace(parkingLot.getTotalParkingSpaces());
        parkingLotAndDates.setParkingLotLocation(parkingLot.getParkingLotLocation());
        parkingLotAndDates.setDate(parkingLot.getParkingLotDate());




        parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates);
        parkingLotAndDateService.addDateToParkingLot(parkingLot.getParkingLotName(), parkingLot.getParkingLotDate());
    */




}
