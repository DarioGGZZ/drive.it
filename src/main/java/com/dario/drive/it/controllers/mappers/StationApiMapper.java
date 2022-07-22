package com.dario.drive.it.controllers.mappers;

import com.dario.drive.it.controllers.to.StationTO;
import com.dario.drive.it.models.bo.StationBO;

import java.util.Objects;

public class StationApiMapper {

    private StationApiMapper() throws IllegalAccessException { throw new IllegalAccessException("This class cannot be instantiated"); }

    public static StationTO convertBOtoTO(StationBO stationBO){
        return StationTO.builder()
                .id(Objects.isNull(stationBO.getId()) ? null : stationBO.getId())
                .name(stationBO.getName())
                .address(stationBO.getAddress())
                .availableBikes(stationBO.getAvailableBikes())
                .totalSlots(stationBO.getTotalSlots())
                .bikes(BikeApiMapper.convertBOlistToTOList(stationBO.getBikes()))
                .build();
    }

    public static StationBO convertTOtoBO(StationTO stationTO){
        return StationBO.builder()
                .id(Objects.isNull(stationTO.getId()) ? null : stationTO.getId())
                .name(stationTO.getName())
                .address(stationTO.getAddress())
                .availableBikes(stationTO.getAvailableBikes())
                .totalSlots(stationTO.getTotalSlots())
                .bikes(BikeApiMapper.convertTOlistToBOList(stationTO.getBikes()))
                .build();
    }
}
