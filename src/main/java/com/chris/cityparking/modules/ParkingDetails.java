package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ParkingDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long parkingDetailID;
    private String numberPlate;
    private String vehicleType;
    private String location;
    //will have to get fixed select list to min error
    private String parkingLotName;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date parkingDate;
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date parkTime;
    private int parkDuration;
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date expiryParkTime;

    /*
    @ManyToOne
    @JoinColumn(name="parkinglot_id")
    @JsonBackReference
    private ParkingLot parkingLot;
    */

    @ManyToOne
    @JoinColumn(name="parkinglot_id")
    @JsonBackReference
    private ParkingLotAndDates parkingLotAndDates;

    /*
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="parkinglot_reg"),
            @JoinColumn(name="date")
    })
    @JsonBackReference
    private ParkingLotAndDates parkingLotAndDates;

     */

}
