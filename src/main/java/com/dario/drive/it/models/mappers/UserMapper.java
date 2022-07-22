package com.dario.drive.it.models.mappers;

import com.dario.drive.it.models.bo.UserBO;
import com.dario.drive.it.models.de.UserDE;

import java.util.Objects;

public class UserMapper {

    private UserMapper() throws IllegalAccessException{
        throw new IllegalAccessException("This class cannot be instantiated");
    }

    public static UserDE convertBOtoDE(UserBO userBO){
        return UserDE.builder()
                .id(Objects.isNull(userBO.getId()) ? null : userBO.getId())
                .name(userBO.getName())
                .surname(userBO.getSurname())
                .dni(userBO.getDni())
                .status(userBO.isStatus())
                .totalHours(userBO.getTotalHours())
                .usingBike(userBO.isUsingBike())
                .bikeId(Objects.isNull(userBO.getBikeId()) ? null : userBO.getBikeId())
                .build();
    }

    public static UserBO convertDEtoBO(UserDE userDE){
        return UserBO.builder()
                .id(userDE.getId())
                .name(userDE.getName())
                .surname(userDE.getSurname())
                .dni(userDE.getDni())
                .status(userDE.isStatus())
                .totalHours(userDE.getTotalHours())
                .usingBike(userDE.isUsingBike())
                .bikeId(Objects.isNull(userDE.getBikeId()) ? null : userDE.getBikeId())
                .build();
    }
}
