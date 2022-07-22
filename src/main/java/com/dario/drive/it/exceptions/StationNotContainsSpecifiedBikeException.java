package com.dario.drive.it.exceptions;

public class StationNotContainsSpecifiedBikeException extends Exception{

    public StationNotContainsSpecifiedBikeException(String message, Throwable error){
        super(message, error);
    }

    public StationNotContainsSpecifiedBikeException(String message){
        super(message);
    }
}
