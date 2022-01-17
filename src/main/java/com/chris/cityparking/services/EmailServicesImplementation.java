package com.chris.cityparking.services;

import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.modules.ParkingDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class EmailServicesImplementation implements EmailServices{


    @Autowired
    public JavaMailSender emailSender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();

    @Override
    public void sendRegistrationMailAttendant(AttendantDetails attendantDetails) {
        log.info("preparing to send registration mail to attendant");

        /*
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lmchris58@gmail.com", "Siijuimimi");
            }
        });

        */

        String sentMessage1 = "Welcome " + attendantDetails.getLastName() + " you have been officially registered a Parking attendant. Below are your login credentials";
        String sentMessage2 = "Email:" + attendantDetails.getEmail();
        String sentMessage3 = "Password:" + attendantDetails.getPassword();
        String sentMessage4 = "You are adviced to change the password immediately after you login successfully";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(attendantDetails.getEmail());
        simpleMailMessage.setSubject("Registration as a Parking Attendant");
        simpleMailMessage.setText(sentMessage1 + sentMessage2 + sentMessage3 + sentMessage4);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");

        /*Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("lmchris58@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(attendantDetails.getEmail()));
            message.setSubject("Registration as a Parking Attendant");
            message.setText("You are now officially registered a Parking attendant");
            message.setText("Your login details are as follows");
            message.setText("Email:" + attendantDetails.getEmail());
            message.setText("Password:" + attendantDetails.getPassword());
            message.setText("You are adviced to change the password immediately after you login successfully");
            message.setSentDate(todaysDate);

            Transport.send(message);

            log.info("Email sent successfully ");
        }
        catch (Exception e){
            e.printStackTrace();
        }

         */

    }

    @Override
    public void sendRegistrationMailMotorist(String motoristEmail) {

        log.info("preparing to send registration mail to motorist");

        String motoristMessage1 = "Welcome " + motoristEmail + ". You are now officially registered as a Parking motorist";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(motoristEmail);
        simpleMailMessage.setSubject("Registration as a Parking motorist");
        simpleMailMessage.setText(motoristMessage1);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");
        /*

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("lmchris58@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(motoristEmail));
            message.setSubject("Registration as a Motorist");
            message.setText("You are now officially registered a Parking motorist");
            message.setSentDate(todaysDate);

            Transport.send(message);

            log.info("Email sent successfully ");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        */
    }

    @Override
    public void sendBookingDetailsMailMotorist(ParkingDetails parkingDetails, String motoristEmail) {
        log.info("preparing to send booking details mail to motorist");

        Date input = new Date();
        LocalDate localParkDate = LocalDate.ofInstant(parkingDetails.getParkingDate().toInstant(), ZoneId.systemDefault());
        LocalTime localParkTime = LocalTime.ofInstant(parkingDetails.getParkTime().toInstant(), ZoneId.of("UTC"));
        LocalTime localExpireTime = LocalTime.ofInstant(parkingDetails.getExpiryParkTime().toInstant(), ZoneId.of("UTC"));



        String motoristBookingMessage1 = "You have successfully booked a parking at " + parkingDetails.getParkingLotName() + " from " +
                localParkTime + "   on    " + localParkDate + "   for   " + parkingDetails.getParkDuration() + "hours  \n";
        String motoristBookingMessage2 = "The set booking will expire at " + localExpireTime + "   on   " + localParkDate
                + " \nPlease note that if you do not remove your vehicle at the said time of expiry action will be taken against you";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(motoristEmail);
        simpleMailMessage.setSubject("Booking details");
        simpleMailMessage.setText(motoristBookingMessage1 + motoristBookingMessage2);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");
/*
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("lmchris58@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(motoristEmail));
            message.setSubject("Booking details");
            message.setText("You have successfully booked a parking at " + parkingDetails.getParkingLotName() + " from " +
                    parkingDetails.getParkTime() + " on " + parkingDetails.getParkingDate() + " for " + parkingDetails.getParkDuration());
            message.setText("The set booking will expire at " + parkingDetails.getExpiryParkTime() + " on " + parkingDetails.getParkingDate());
            message.setText("Please note that if you do not remove your vehicle at the said time of expiry action will be taken against you");
            message.setSentDate(todaysDate);

            Transport.send(message);

            log.info("Email sent successfully ");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        */
    }
}
