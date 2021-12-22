package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Date parkingDate;
    @Temporal(TemporalType.TIME)
    private Date parkTime;
    private int parkDuration;

    @ManyToOne
    @JoinColumn(name="parkinglot_id")
    @JsonBackReference
    private ParkingLot parkingLot;

}
