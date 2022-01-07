package com.chris.cityparking.services;

import com.chris.cityparking.modules.BookingDates;
import com.chris.cityparking.modules.ParkingLotAndDates;

import java.util.Date;
import java.util.List;

public interface ParkingLotAndDateService {
    ParkingLotAndDates saveParkLotAndDate(ParkingLotAndDates parkingLotAndDates);
    BookingDates saveBookingDates(BookingDates bookingDates);
    void addDateToParkingLot(String parkingLotName, Date date);
    List<ParkingLotAndDates> getParkingLotAndDates(String parkingLotName);
    List<ParkingLotAndDates> getListByDates(Date date);
    List<ParkingLotAndDates> getAllParkingLotAndDates();
}
