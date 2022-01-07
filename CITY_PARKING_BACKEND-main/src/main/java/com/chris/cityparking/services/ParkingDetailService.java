package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingDetails;

import java.util.List;

public interface ParkingDetailService {
    void createParkingDetail(ParkingDetails parkingDetails);
    List<ParkingDetails> getParkingDetail(String numberPlate);
    List<ParkingDetails> getAllParkingDetails();
    void updateParkingDetail(ParkingDetails parkingDetails);
    void deleteParkingDetails(String numberPlate);
    void deleteAllParkingDetails();
}
