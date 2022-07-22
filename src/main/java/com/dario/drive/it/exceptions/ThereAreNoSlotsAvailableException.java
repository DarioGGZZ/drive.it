package com.dario.drive.it.exceptions;

public class ThereAreNoSlotsAvailableException extends Exception{

    public ThereAreNoSlotsAvailableException(String message, Throwable error){
        super(message, error);
    }

    public ThereAreNoSlotsAvailableException(String message){
        super(message);
    }
}
