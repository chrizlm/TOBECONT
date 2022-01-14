package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingLotToDB;
import com.chris.cityparking.repositories.ParkingLotToDBRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingLotToolsServiceImplementation implements ParkingLotToolsService{
    @Autowired
    ParkingLotToDBRepo parkingLotToDBRepo;

    @Override
    public void handleDatesForParking(ParkingLotToDB parkingLotToDB) {

    }
}
