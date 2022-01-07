package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingLotId;
    private String parkingRegNo;
    private String parkingLotLocation;
    //unique
    private String parkingLotName;
    private int totalParkingSpaces;


    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ParkingDetails> parkingDetails;

    private int availableSpace;

    @Temporal(TemporalType.DATE)
    private Date parkingLotDate;
   /*
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<BookingDates> bookingDates = new ArrayList<>();

    */





    /* private int regularSpaces;
    private int vipSpace;
    private int specialSpace;



     */



}
