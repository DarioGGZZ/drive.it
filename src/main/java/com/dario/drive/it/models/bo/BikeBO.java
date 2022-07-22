package com.dario.drive.it.models.bo;

import com.dario.drive.it.enums.BikeAvailabilityEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BikeBO {

    private Long id;
    private BikeAvailabilityEnum status;
    private double travelHours;
    private double usefulLife;
    /*private StationBO station;*/

    public void broke(){
        this.status = BikeAvailabilityEnum.BROKEN;
    }

    public void takeToWorkshop(){
        this.status = BikeAvailabilityEnum.REPAIRING;
    }

    public void repair(){
        this.status = BikeAvailabilityEnum.FREE;
    }

    public void use(){
        this.status = BikeAvailabilityEnum.IN_USE;
    }

    public void returned(double totalHours){
        travelHours += totalHours;
        usefulLife -= totalHours;
        this.status = BikeAvailabilityEnum.FREE;
    }
}
