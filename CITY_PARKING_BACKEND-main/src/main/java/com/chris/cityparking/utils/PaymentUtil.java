package com.chris.cityparking.utils;

import org.springframework.stereotype.Service;

@Service
public class PaymentUtil {
    public double calcPayment(int duration, String vehicleType){
        //small cars (rate) * duration
        return 0.01;
    }
}
