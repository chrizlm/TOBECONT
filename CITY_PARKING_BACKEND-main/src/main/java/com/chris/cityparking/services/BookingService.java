package com.chris.cityparking.services;


import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.repositories.LocationParkAreasRepo;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookingService {
    @Autowired
    ParkingLotRepo parkingLotRepo;

    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;

    @Autowired
    LocationParkAreasRepo locationParkAreasRepo;


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
    public int getAvailableSpace(String parkingLotName){
        ParkingLot parkingLot = parkingLotRepo.getByParkingLotName(parkingLotName);
        int totalCapacity = parkingLot.getTotalParkingSpaces();
        int occupiedCapacity = getOccupiedSpaces(parkingLotName);
        int freeSpaces = totalCapacity - occupiedCapacity;
        return freeSpaces;
    }


    /*
    //retrieve a list of bookings
    get specific parking lot
    retrieve all the bookings
     */
    public List<ParkingDetails> getAllParkingDetailsInAParkingLot(String parkingLotName){
        return parkingDetailsRepo.getByParkingLotName(parkingLotName);
    }


    //add a booking
    //remove a booking

    /*
    //location and parkinglots for dependant dropdownlist
     */

    public Set<String> getAreas(){
        return locationParkAreasRepo.findLocation();
    }

    public List<String> getParkings(String locationName){
        return locationParkAreasRepo.findParkingLots(locationName);
    }

   /*
    public String getJsonStringLocation(){
       List<LocationParkAreas> locationList = locationParkAreasRepo.findAll();

       for(LocationParkAreas eachLoction : locationList){
          return locationAreaObj(eachLoction.getLocation(), eachLoction.getParkingLotName());
       }
        return "no area list to return";
    }


        public String locationAreaObj(String location, String parkingLotName){

        JSONObject jsonObject = new JSONObject();
        JSONArray kasaraniJsonArray = new JSONArray();
        JSONArray mombasaRoadJsonArray = new JSONArray();
        JSONArray karenJsonArray = new JSONArray();

        switch (location){
            case "KASARANI":
                kasaraniJsonArray.add(parkingLotName);
                jsonObject.put("lots", kasaraniJsonArray);
                break;
            case "MOMBASAROAD":
                mombasaRoadJsonArray.add(parkingLotName);
                jsonObject.put("lots", mombasaRoadJsonArray);
                break;
            case "KAREN":
                karenJsonArray.add(parkingLotName);
                jsonObject.put("lots", karenJsonArray);
                break;
            default:
                System.out.println("location not found");
                break;
        }
        return jsonObject.toJSONString();

    }

    */



}
