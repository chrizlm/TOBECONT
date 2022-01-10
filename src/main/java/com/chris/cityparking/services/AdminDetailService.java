package com.chris.cityparking.services;

import com.chris.cityparking.modules.AdminDetails;

public interface AdminDetailService {
    void createAdminDetail(AdminDetails adminDetails);
    void updateAdminDetail(AdminDetails adminDetails);
    void deleteAdminDetail(Long adminID);
}
