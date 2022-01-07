package com.chris.cityparking.services;


import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.LocationParkAreasRepo;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class BookingServiceImplementation implements BookingService{
    @Autowired
    ParkingLotRepo parkingLotRepo;

    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;

    @Autowired
    LocationParkAreasRepo locationParkAreasRepo;

    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;


    /*
    //get capacity of a parking lot
    use repo to retrieve the capacity
     */


    /*
    //get parking lots in a location
    use repo to retrieve the parking lots
     */


    /*
    //get occupied spaces
    use the parking lot name in parking lot repo to find a parking lot
    then use the count method to determine the number of bookings made
     */
    @Override
    public int getOccupiedSpaces(String parkingLotName){
        return parkingDetailsRepo.getByParkingLotName(parkingLotName).size();
    }


    /*
    //get available free spaces
    find a parking lot
    get the total capacity
    then get the occupied spaces
    return the difference of the two (occupied from total capacity)
     */
    @Override
    public int getAvailableSpace(String parkingLotName){
        ParkingLot parkingLot = parkingLotRepo.getByParkingLotName(parkingLotName);
        int totalCapacity = parkingLot.getTotalParkingSpaces();
        int occupiedCapacity = getOccupiedSpaces(parkingLotName);
        int freeSpaces = (totalCapacity - occupiedCapacity);
        parkingLot.setAvailableSpace(freeSpaces);
        return freeSpaces;
    }

    @Override
    public void getAvailableFreeSpacePerDate(String parkingLotName){
        ParkingLotAndDates parkingLotAndDates = parkingLotAndDatesRepo.getByParkingLotName(parkingLotName);
        int totalCapacity = parkingLotAndDates.getTotalCapacity();
        int occupiedCapacity = getOccupiedSpaces(parkingLotName);
        int freeSpaces = (totalCapacity - occupiedCapacity);
        parkingLotAndDates.setAvailableSpace(freeSpaces);
    }

    @Override
    public void updateFreeSpace(ParkingLot parkingLot, Date selectedDate){
        int totalCapacity = parkingLot.getTotalParkingSpaces();
        int occupiedCapacity = getOccupiedSpaces(parkingLot.getParkingLotName());
        int freeSpaces = ((totalCapacity - 1) - occupiedCapacity);
        ParkingLot PL = parkingLotRepo.getByParkingLotName(parkingLot.getParkingLotName());
        PL.setAvailableSpace(freeSpaces);
        PL.setParkingLotDate(selectedDate);
        //parkingLot.setAvailableSpace(freeSpaces);
        parkingLotRepo.save(PL);

        //check date


        /*
        * if statement or case
        * */

        /*
        LocalDateTime today =  LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        if(selectedDate.equals(today)){
            int totalCapacity = parkingLot.getTotalParkingSpaces();
            int occupiedCapacity = getOccupiedSpaces(parkingLot.getParkingLotName());
            int freeSpaces = ((totalCapacity - 1) - occupiedCapacity);
            ParkingLot PL = parkingLotRepo.getByParkingLotName(parkingLot.getParkingLotName());
            PL.setAvailableSpace(freeSpaces);
            PL.setParkingLotDate(selectedDate);
            //parkingLot.setAvailableSpace(freeSpaces);
            parkingLotRepo.save(PL);
        }else if(selectedDate.equals(tomorrow)){
            int totalCapacity = parkingLot.getTotalParkingSpaces();
            int occupiedCapacity = getOccupiedSpaces(parkingLot.getParkingLotName());
            int freeSpaces = ((totalCapacity - 1) - occupiedCapacity);
            ParkingLot PL = parkingLotRepo.getByParkingLotName(parkingLot.getParkingLotName());
            PL.setAvailableSpace(freeSpaces);
            PL.setParkingLotDate(selectedDate);
            //parkingLot.setAvailableSpace(freeSpaces);
            parkingLotRepo.save(PL);
        }

         */



        //processes

    }


    /*
    //retrieve a list of bookings
    get specific parking lot
    retrieve all the bookings
     */
    @Override
    public List<ParkingDetails> getAllParkingDetailsInAParkingLot(String parkingLotName){
        return parkingDetailsRepo.getByParkingLotName(parkingLotName);
    }


    //add a booking
    //remove a booking

    /*
    //location and parkinglots for dependant dropdownlist
     */

    @Override
    public Set<String> getAreas(){
        return locationParkAreasRepo.findLocation();
    }

    @Override
    public List<String> getParkings(String locationName){
        return locationParkAreasRepo.findParkingLots(locationName);
    }





}
