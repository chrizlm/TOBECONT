package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@IdClass(PKID.class)
public class ParkingLotAndDates {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    private String parkingLotLocation;


    @Size(max = 40)
    private String parkingLotName;

    //@NaturalId
    //@NotBlank
    //@Size(max = 40)
    private String regNo;


    private Integer totalCapacity;

    private Integer availableSpace = 5;


    //@NaturalId
    @Column(length = 60)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;

/*
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<BookingDates> bookingDates = new ArrayList<>();

 */


    @OneToMany(mappedBy = "parkingLotAndDates", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ParkingDetails> parkingDetails;



}
