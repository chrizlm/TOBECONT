package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.MotoristDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristDetailsRepo extends JpaRepository<MotoristDetails, Long> {
    List<MotoristDetails> getByMotoristID(Long motoristID);
}
