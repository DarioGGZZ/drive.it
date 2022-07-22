package com.dario.drive.it.models.mappers;

import com.dario.drive.it.models.bo.StationBO;
import com.dario.drive.it.models.de.StationDE;
import java.util.Objects;

public class StationMapper {

    private StationMapper() throws IllegalAccessException {
        throw new IllegalAccessException("This class cannot be instantiated");
    }

    public static StationDE convertBOtoDE(StationBO stationBO){
        return StationDE.builder()
                .id(Objects.isNull(stationBO.getId()) ? null : stationBO.getId())
                .name(stationBO.getName())
                .address(stationBO.getAddress())
                .availableBikes(stationBO.getAvailableBikes())
                .totalSlots(stationBO.getTotalSlots())
                .bikes(BikeMapper.convertBOlistToDEList(stationBO.getBikes()))
                .build();
    }

    public static StationBO convertDEtoBO(StationDE stationDE){
        return StationBO.builder()
                .id(Objects.isNull(stationDE.getId()) ? null : stationDE.getId())
                .name(stationDE.getName())
                .address(stationDE.getAddress())
                .availableBikes(stationDE.getAvailableBikes())
                .totalSlots(stationDE.getTotalSlots())
                .bikes(BikeMapper.convertDElistToBOList(stationDE.getBikes()))
                .build();
    }
}
