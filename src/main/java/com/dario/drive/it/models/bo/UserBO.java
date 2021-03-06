package com.dario.drive.it.models.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBO {

    private Long id;
    private String name;
    private String surname;
    private Long dni;
    private boolean usingBike;
    private Long bikeId;
    private double totalHours;
    private boolean status;

    public void deactivate(){
        this.status = false;
    }

    public void activate(){
        this.status = true;
    }

    public void rentBike(Long id){
        setBikeId(id);
        this.usingBike = true;
    }

    public void returnBike(double timeUsed){
        totalHours += timeUsed;
        setBikeId(null);
        this.usingBike = false;
    }
}
