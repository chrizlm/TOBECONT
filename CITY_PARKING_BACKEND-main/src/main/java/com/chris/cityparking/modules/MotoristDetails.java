package com.chris.cityparking.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MotoristDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long motoristID; //national ID
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String password;

}
