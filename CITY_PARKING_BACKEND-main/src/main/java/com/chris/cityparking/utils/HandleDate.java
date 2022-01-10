package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HandleDate {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date parkingDate;
    public Date handleDesOfDate(ParkingDetails parkingDetails){
        ParkingDetails parkingDetails1 = new ParkingDetails();
        this.parkingDate = parkingDetails.getParkingDate();
        return this.parkingDate;

    }
}
