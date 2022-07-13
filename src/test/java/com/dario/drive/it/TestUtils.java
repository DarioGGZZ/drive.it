package com.dario.drive.it;

import com.dario.drive.it.controllers.to.UserTO;
import com.dario.drive.it.enums.UserStatusEnum;
import com.dario.drive.it.models.bo.UserBO;
import com.dario.drive.it.models.de.UserDE;

public class TestUtils {

    public static UserDE getUserDE(){
        return UserDE.builder()
                .id(1L)
                .dni(38563674L)
                .name("testName")
                .surname("testSurname")
                .usingBike(false)
                .totalHours(0.0d)
                .status(true)
                .build();
    }

    public static UserBO getUserBO(){
        return UserBO.builder()
                .id(1L)
                .dni(38563674L)
                .name("testName")
                .surname("testSurname")
                .usingBike(false)
                .totalHours(0.0d)
                .status(true)
                .build();
    }

    public static UserTO getUserTO(){
        return UserTO.builder()
                .id(1L)
                .dni(38563674L)
                .name("testName")
                .surname("testSurname")
                .usingBike(false)
                .totalHours(0.0d)
                .status(UserStatusEnum.ACTIVE.name())
                .build();
    }
}
