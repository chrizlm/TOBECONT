package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.ParkingLotAndDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkingLotAndDatesRepo extends JpaRepository<ParkingLotAndDates, Long> {
    ParkingLotAndDates getByParkingLotName(String parkingLotName);
    List<ParkingLotAndDates> getByDate(Date date);
}
