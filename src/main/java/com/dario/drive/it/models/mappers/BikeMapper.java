package com.dario.drive.it.models.mappers;

import com.dario.drive.it.enums.BikeAvailabilityEnum;
import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.de.BikeDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BikeMapper {

    private BikeMapper() throws IllegalAccessException {
        throw new IllegalAccessException("This class cannot be instantiated");
    }

    public static BikeDE convertBOtoDE(BikeBO bikeBO){
        return BikeDE.builder()
                .id(Objects.isNull(bikeBO.getId()) ? null : bikeBO.getId())
                .status(bikeBO.getStatus().name())
                .available(bikeBO.getStatus().isAvailable())
                .traveledHours(bikeBO.getTravelHours())
                .usefulLife(bikeBO.getUsefulLife())
                //.station(StationMapper.convertBOtoDE(bikeBO.getStation()))
                .build();
    }

    public static BikeBO convertDEtoBO(BikeDE bikeDE){
        return BikeBO.builder()
                .id(Objects.isNull(bikeDE.getId()) ? null : bikeDE.getId())
                .status(BikeAvailabilityEnum.getAvailabilityByString(bikeDE.getStatus()))
                .travelHours(bikeDE.getTraveledHours())
                .usefulLife(bikeDE.getUsefulLife())
                //.station(StationMapper.convertDEtoBO(bikeDE.getStation()))
                .build();
    }

    public static List<BikeBO> convertDElistToBOList(List<BikeDE> bikes){
        List<BikeBO> bikesBO = new ArrayList<>();
        bikes.forEach(bike -> bikesBO.add(BikeMapper.convertDEtoBO(bike)));
        return bikesBO;
    }

    public static List<BikeDE> convertBOlistToDEList(List<BikeBO> bikes){
        List<BikeDE> bikesDE = new ArrayList<>();
        bikes.forEach(bike -> bikesDE.add(BikeMapper.convertBOtoDE(bike)));
        return bikesDE;
    }
}
