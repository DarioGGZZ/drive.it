package com.dario.drive.it.controllers.services;

import com.dario.drive.it.enums.BikeAvailabilityEnum;
import com.dario.drive.it.exceptions.StationNotContainsSpecifiedBikeException;
import com.dario.drive.it.exceptions.ThereAreNoSlotsAvailableException;
import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.bo.StationBO;
import com.dario.drive.it.models.bo.UserBO;
import com.dario.drive.it.services.BikeService;
import com.dario.drive.it.services.StationService;
import com.dario.drive.it.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    StationService stationService;

    @Autowired
    BikeService bikeService;

    @Autowired
    UserService userService;


    public void getBikeFromStationToUser(String stationName, Long id, Long userDni) throws StationNotContainsSpecifiedBikeException {
        BikeBO bike = stationService.getBikeFromStation(stationName, id);
        UserBO userBO = userService.getUserByDni(userDni);
        userBO.rentBike(id);
        bike.use();
        bikeService.updateBike(bike, bike.getId());
        userService.updateUser(userBO, userBO.getId());
    }

    public void userReturnBikeToStation(String stationName, Long userDni, double timeUsed) throws ThereAreNoSlotsAvailableException{
        StationBO stationBO = stationService.findByName(stationName);
        if(!stationBO.hasEmptySlot()){
            throw new ThereAreNoSlotsAvailableException("Station with name " + stationName + " has not space");
        }
        UserBO userBO = userService.getUserByDni(userDni);
        BikeBO bike = bikeService.getBike(userBO.getBikeId());
        userBO.returnBike(timeUsed);
        bike.returned(timeUsed);
        stationBO.addBike(bike);
        stationService.updateStation(stationBO, stationBO.getId());
        bikeService.updateBike(bike, bike.getId());
        userService.updateUser(userBO, userBO.getId());
    }
}
