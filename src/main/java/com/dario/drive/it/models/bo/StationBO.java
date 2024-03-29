package com.dario.drive.it.models.bo;

import com.dario.drive.it.enums.BikeAvailabilityEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StationBO {

    private Long id;
    private String name;
    private String address;
    private int availableBikes;
    private int totalSlots;
    private List<BikeBO> bikes;

    public void addBike(BikeBO bikeBO){
        if(totalSlots > 0){
            bikes.add(bikeBO);
            setAvailableBikes(bikeBO);
        }
    }

    public void setAvailableBikes(){
        availableBikes++;
    }

    public void setAvailableBikes(BikeBO bikeBO){
        if(bikeBO.getStatus().equals(BikeAvailabilityEnum.FREE)){
            setAvailableBikes();
        }
    }

    public boolean containsBikeWithId(Long id){
        for(BikeBO bikeBO : bikes){
            if(bikeBO.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public BikeBO getBikeWithId(Long id){
        BikeBO bike = null;
        for(BikeBO bikeBO : bikes){
            if(bikeBO.getId().equals(id)){
                bike = bikes.remove(bikes.indexOf(bikeBO));
            }
        }
        return bike;
    }

    public boolean hasEmptySlot(){
        return (totalSlots - availableBikes) > 0;
    }
}
