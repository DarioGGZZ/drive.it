package com.dario.drive.it.enums;

import lombok.Getter;

@Getter
public enum BikeAvailabilityEnum {
    FREE(true),
    REPAIRING(false),
    BROKEN(false),
    IN_USE(false);

    private boolean isAvailable;

    BikeAvailabilityEnum(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public static BikeAvailabilityEnum getAvailabilityByString(String string){
        for(BikeAvailabilityEnum bikeAvailabilityEnum : BikeAvailabilityEnum.values()){
            if(bikeAvailabilityEnum.name().equals(string)){
                return bikeAvailabilityEnum;
            }
        }
        throw new IllegalArgumentException("String " + string + " is not a valid bike status");
    }
}
