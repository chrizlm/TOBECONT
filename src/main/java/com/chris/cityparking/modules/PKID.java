package com.chris.cityparking.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class PKID implements Serializable {
    @Id
    private String parkingLotLocation;


    @Id
    private String parkingLotName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;
}
