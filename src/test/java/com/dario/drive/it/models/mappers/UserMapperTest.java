package com.dario.drive.it.models.mappers;

import com.dario.drive.it.TestUtils;
import com.dario.drive.it.models.bo.UserBO;
import com.dario.drive.it.models.de.UserDE;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserMapperTest {

    UserBO expectedBO;
    UserDE expectedDE;

    @BeforeAll
    public void setUp(){
        expectedBO = TestUtils.getUserBO();
        expectedDE = TestUtils.getUserDE();
    }

    @Test
    void convertBOtoDETest(){
        UserDE resultDE = UserMapper.convertBOtoDE(expectedBO);
        assertEquals(expectedDE.getDni(), resultDE.getDni());
        assertEquals(expectedDE.getName(), resultDE.getName());
        assertEquals(expectedDE.getSurname(), resultDE.getSurname());
        assertEquals(expectedDE.getTotalHours(), resultDE.getTotalHours());
        assertEquals(expectedDE.isStatus(), resultDE.isStatus());
        assertEquals(expectedDE.isUsingBike(), resultDE.isUsingBike());
    }

    @Test
    void convertDEtoBOTest(){
        UserBO resultBO = UserMapper.convertDEtoBO(expectedDE);
        assertEquals(expectedBO.getDni(), resultBO.getDni());
        assertEquals(expectedBO.getName(), resultBO.getName());
        assertEquals(expectedBO.getSurname(), resultBO.getSurname());
        assertEquals(expectedBO.getTotalHours(), resultBO.getTotalHours());
        assertEquals(expectedBO.isStatus(), resultBO.isStatus());
        assertEquals(expectedBO.isUsingBike(), resultBO.isUsingBike());
    }
}
