package com.chris.cityparking.repositories;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.LocationAndDateForm;
import com.chris.cityparking.modules.ParkingLotAndDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDetailsRepo extends JpaRepository<AdminDetails, Long> {
    AdminDetails getByEmail(String email);
    AdminDetails findByEmail(String email);

    @Modifying
    @Query("UPDATE AdminDetails a SET a.password = :password WHERE a.email = :email")
    void updatePassword(@Param(value = "password") String password, @Param(value = "email") String email);


}

/*
@Modifying
    @Query("UPDATE AdminDetails a SET a.password = :#{#emailPasswordForm.newpassword} WHERE a.email = :#{#emailPasswordForm.email}")
    void updatePassword(@Param("emailPasswordForm")EmailPasswordForm emailPasswordForm);

 */