package com.dario.drive.it.controllers.mappers;

import com.dario.drive.it.TestUtils;
import com.dario.drive.it.controllers.to.UserTO;
import com.dario.drive.it.enums.UserStatusEnum;
import com.dario.drive.it.models.bo.UserBO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserApiMapperTest {

    UserBO expectedBO;
    UserTO expectedTO;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getUserBO();
        expectedTO = TestUtils.getUserTO();
    }

    @Test
    void convertBOtoTOTest(){
        UserTO resultTO = UserApiMapper.convertBOtoTO(expectedBO);
        assertEquals(expectedTO.getDni(), resultTO.getDni());
        assertEquals(expectedTO.getName(), resultTO.getName());
        assertEquals(expectedTO.getSurname(), resultTO.getSurname());
        assertEquals(expectedTO.getTotalHours(), resultTO.getTotalHours());
        assertEquals(expectedTO.isUsingBike(), resultTO.isUsingBike());
        assertEquals(UserStatusEnum.ACTIVE.name(), resultTO.getStatus());
    }

    @Test
    void convertTOtoBOTest(){
        UserBO resultBO = UserApiMapper.convertTOtoBO(expectedTO);
        assertEquals(expectedBO.getDni(), resultBO.getDni());
        assertEquals(expectedBO.getName(), resultBO.getName());
        assertEquals(expectedBO.getSurname(), resultBO.getSurname());
        assertEquals(expectedBO.getTotalHours(), resultBO.getTotalHours());
        assertEquals(expectedBO.isUsingBike(), resultBO.isUsingBike());
        assertTrue(resultBO.isStatus());
    }
}
