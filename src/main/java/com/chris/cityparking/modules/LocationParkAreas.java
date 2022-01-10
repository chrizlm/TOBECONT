package com.chris.cityparking.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LocationParkAreas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String location;
    private String parkingLotName;


    /*
    public JSONObject locationAreaObj(String location, String parkingLotName){
        this.location = location;
        this.parkingLotName = parkingLotName;

        JSONObject jsonObject = new JSONObject();
        JSONArray kasaraniJsonArray = new JSONArray();
        JSONArray mombasaRoadJsonArray = new JSONArray();
        JSONArray karenJsonArray = new JSONArray();

        switch (location){
            case "KASARANI":
                kasaraniJsonArray.add(parkingLotName);
                jsonObject.put("KASARANI", kasaraniJsonArray);
                break;
            case "MOMBASAROAD":
                mombasaRoadJsonArray.add(parkingLotName);
                jsonObject.put("MOMBASAROAD", mombasaRoadJsonArray);
                break;
            case "KAREN":
                karenJsonArray.add(parkingLotName);
                jsonObject.put("KAREN", karenJsonArray);
                break;
            default:
                System.out.println("location not found");
                break;
        }

        return jsonObject;
    }
    */






}

