package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ParkingLotAndDates {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Size(max = 40)
    private String parkingLotName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    private String parkingRegNo;


    private int totalCapacity;
    private int availableSpace;


    @NaturalId
    @Column(length = 60)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<BookingDates> bookingDates = new ArrayList<>();

}
