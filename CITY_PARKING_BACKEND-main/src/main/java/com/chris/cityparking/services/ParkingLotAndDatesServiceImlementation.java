package com.chris.cityparking.services;

import com.chris.cityparking.modules.BookingDates;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.BookingDateRepo;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingLotAndDatesServiceImlementation implements ParkingLotAndDateService{
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;
    @Autowired
    BookingDateRepo bookingDateRepo;


    @Override
    public ParkingLotAndDates saveParkLotAndDate(ParkingLotAndDates parkingLotAndDates) {
        log.info("saving parking lot with date {} to the database", parkingLotAndDates.getParkingLotName());
        return parkingLotAndDatesRepo.save(parkingLotAndDates);
    }

    @Override
    public BookingDates saveBookingDates(BookingDates bookingDates) {
        log.info("saving new date {} to the database", bookingDates.getDate());
        return bookingDateRepo.save(bookingDates);

    }

    @Override
    public void addDateToParkingLot(String parkingLotName, Date date) {
        log.info("Adding date {} to parking lot {}", date, parkingLotName);
        ParkingLotAndDates parkingLotAndDates = parkingLotAndDatesRepo.getByParkingLotName(parkingLotName);
        BookingDates bookingDates = bookingDateRepo.findByDate(date);

        parkingLotAndDates.getBookingDates().add(bookingDates);
    }

    @Override
    public List<ParkingLotAndDates> getParkingLotAndDates(String parkingLotName) {
        log.info("fetching parking lot and date {} ", parkingLotName);
        return (List<ParkingLotAndDates>) parkingLotAndDatesRepo.getByParkingLotName(parkingLotName);
    }

    @Override
    public List<ParkingLotAndDates> getListByDates(Date date) {
        log.info("fetch list by date {}", date);
        return parkingLotAndDatesRepo.getByDate(date);
    }

    @Override
    public List<ParkingLotAndDates> getAllParkingLotAndDates() {
        log.info("fetching all parking lot and date");
        return parkingLotAndDatesRepo.findAll();
    }
}
