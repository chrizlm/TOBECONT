package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.ParkingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingDetailsRepo extends JpaRepository<ParkingDetails, Long> {

    Optional<ParkingDetails> findByNumberPlate(String numberPlate);
    ParkingDetails getByNumberPlate(String numberPlate);
    void deleteByNumberPlate(String numberPlate);
    List<ParkingDetails> getByParkingLotName(String ParkingLotName);

}
