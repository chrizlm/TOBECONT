package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.ParkingLotNotFoundException;
import com.chris.cityparking.modules.LocationParkAreas;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.repositories.LocationParkAreasRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {
    @Autowired
    ParkingLotRepo parkingLotRepo;
    @Autowired
    LocationParkAreasRepo locationParkAreasRepo;
     /*
    getting details
    saving details
    update details
    deleting details
     */

    public void createParkingLot(ParkingLot parkingLot){
        LocationParkAreas locationParkAreas = new LocationParkAreas();
        locationParkAreas.setLocation(parkingLot.getParkingLotLocation());
        locationParkAreas.setParkingLotName(parkingLot.getParkingLotName());
        locationParkAreasRepo.save(locationParkAreas);
        parkingLotRepo.save(parkingLot);
    }

    public ParkingLot getParkingLot(String parkingRegNo){
        return parkingLotRepo.getByParkingRegNo(parkingRegNo);
    }

    public List<ParkingLot> getAllParkingLots(){
        return parkingLotRepo.findAll();
    }

    public void updateParkingLot(ParkingLot parkingLot){
        parkingLotRepo.findByParkingRegNo(parkingLot.getParkingRegNo()).ifPresentOrElse(parklot ->{
            parklot.setParkingLotLocation(parkingLot.getParkingLotLocation());
            parklot.setParkingLotName(parkingLot.getParkingLotName());
            parklot.setTotalParkingSpaces(parkingLot.getTotalParkingSpaces());
            parkingLotRepo.save(parklot);
        }, () ->{
            throw new ParkingLotNotFoundException(parkingLot.getParkingRegNo());
        });
    }

    public void deleteParkingLot(String parkingRegNo){
        parkingLotRepo.deleteByParkingRegNo(parkingRegNo);
    }

    public void deleteAll(){
        parkingLotRepo.deleteAll();
    }

    /**
     * other services associated with parking lot
     *
     * -findAll in a location
     * -Get specific with name and location
     * -get the number of spaces in a parking
     */

    public List<ParkingLot> getParkingLotsInLocation(String location){
        return parkingLotRepo.findAllByParkingLotLocation(location);
    }

    public Optional<ParkingLot> getParkingLotByName(String parkingName){
        return parkingLotRepo.findParkingLotByParkingLotName(parkingName);
    }



}
