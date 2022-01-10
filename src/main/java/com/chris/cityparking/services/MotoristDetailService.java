package com.chris.cityparking.services;


import com.chris.cityparking.modules.MotoristDetails;

import java.util.List;

public interface MotoristDetailService {
    void createMotoristDetail(MotoristDetails motoristDetails);
    List<MotoristDetails> getMotorist(Long motoristID);
    List<MotoristDetails> getAllMotorists();
    void updateMotorist(MotoristDetails motoristDetails);
    void deleteMotorist(Long motoristID);
}