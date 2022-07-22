package com.dario.drive.it.controllers.mappers;

import com.dario.drive.it.controllers.to.UserTO;
import com.dario.drive.it.enums.UserStatusEnum;
import com.dario.drive.it.models.bo.UserBO;

import java.util.Objects;

public class UserApiMapper {

    private UserApiMapper() throws IllegalAccessException { throw new IllegalAccessException("This class cannot be instantiated"); }

    public static UserTO convertBOtoTO(UserBO userBO){
        return UserTO.builder()
                .id(userBO.getId())
                .name(userBO.getName())
                .surname(userBO.getSurname())
                .dni(userBO.getDni())
                .status(userBO.isStatus() ? UserStatusEnum.ACTIVE.name() : UserStatusEnum.NOT_ACTIVE.name())
                .totalHours(userBO.getTotalHours())
                .usingBike(userBO.isUsingBike())
                .bikeId(Objects.isNull(userBO.getBikeId()) ? null : userBO.getBikeId())
                .build();
    }

    public static UserBO convertTOtoBO(UserTO userTO){
        return UserBO.builder()
                .id(Objects.isNull(userTO.getId()) ? null : userTO.getId())
                .name(userTO.getName())
                .surname(userTO.getSurname())
                .dni(userTO.getDni())
                .status(userTO.getStatus().equals(UserStatusEnum.ACTIVE.name()))
                .totalHours(userTO.getTotalHours())
                .usingBike(userTO.isUsingBike())
                .bikeId(Objects.isNull(userTO.getBikeId()) ? null : userTO.getBikeId())
                .build();
    }
}
