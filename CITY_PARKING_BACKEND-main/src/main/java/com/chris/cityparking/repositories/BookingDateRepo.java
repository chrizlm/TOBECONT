package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.BookingDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingDateRepo extends JpaRepository<BookingDates, Long> {
    BookingDates findByDate(Date date);
}
