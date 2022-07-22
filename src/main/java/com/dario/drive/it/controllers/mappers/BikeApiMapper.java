package com.dario.drive.it.controllers.mappers;

import com.dario.drive.it.controllers.to.BikeTO;
import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.de.BikeDE;
import com.dario.drive.it.models.mappers.BikeMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BikeApiMapper {

    private BikeApiMapper() throws IllegalAccessException {
        throw new IllegalAccessException("This class cannot be instantiated");
    }

    public static BikeTO convertBOtoTO(BikeBO bikeBO){
        return BikeTO.builder()
                .id(Objects.isNull(bikeBO.getId()) ? null : bikeBO.getId())
                .status(bikeBO.getStatus())
                .travelHours(bikeBO.getTravelHours())
                .usefulLife(bikeBO.getUsefulLife())
                //.station(StationApiMapper.convertBOtoTO(bikeBO.getStation()))
                .build();
    }

    public static BikeBO convertTOtoBO(BikeTO bikeTO){
        return BikeBO.builder()
                .id(Objects.isNull(bikeTO.getId()) ? null : bikeTO.getId())
                .status(bikeTO.getStatus())
                .travelHours(bikeTO.getTravelHours())
                .usefulLife(bikeTO.getUsefulLife())
                //.station(StationApiMapper.convertTOtoBO(bikeTO.getStation)))
                .build();
    }

    public static List<BikeBO> convertTOlistToBOList(List<BikeTO> bikes){
        List<BikeBO> bikesBO = new ArrayList<>();
        bikes.forEach(bike -> bikesBO.add(BikeApiMapper.convertTOtoBO(bike)));
        return bikesBO;
    }

    public static List<BikeTO> convertBOlistToTOList(List<BikeBO> bikes){
        List<BikeTO> bikesTO = new ArrayList<>();
        bikes.forEach(bike -> bikesTO.add(BikeApiMapper.convertBOtoTO(bike)));
        return bikesTO;
    }
}
