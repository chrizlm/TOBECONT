package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDetailsRepo extends JpaRepository<AdminDetails, Long> {
}
