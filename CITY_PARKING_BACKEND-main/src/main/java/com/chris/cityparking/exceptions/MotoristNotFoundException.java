package com.chris.cityparking.exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class MotoristNotFoundException extends RuntimeException{
    public MotoristNotFoundException(Long motoristID){
        super("could not find motorist with id " + motoristID);
    }
}
