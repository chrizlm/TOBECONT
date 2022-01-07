package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLot;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookingService {
    int getOccupiedSpaces(String parkingLotName);
    int getAvailableSpace(String parkingLotName);
    void getAvailableFreeSpacePerDate(String parkingLotName);
    void updateFreeSpace(ParkingLot parkingLot, Date selectedDate);
    List<ParkingDetails> getAllParkingDetailsInAParkingLot(String parkingLotName);
    Set<String> getAreas();
    List<String> getParkings(String locationName);
}
