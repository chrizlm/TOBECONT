package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ParkingDetailForm {
    private Long parkingDetailID;
    private String numberPlate;
    private String vehicleType;
    private String location;
    //will have to get fixed select list to min error
    private String parkingLotName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date parkingDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date parkTime;
    private int parkDuration;

    /*
    @ManyToOne
    @JoinColumn(name="parkinglot_id")
    @JsonBackReference
    private ParkingLot parkingLot;
    */


}
