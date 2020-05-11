package org.itstep.data.parse;

import org.itstep.dto.UserDto;
import org.itstep.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(locations = {"classpath:spring-db.xml", "classpath:spring-mvc.xml"})
class DataUserTest {

    private final Logger log = LoggerFactory.getLogger(DataEquipmentTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataUser dataUser;

    @Test
    @DirtiesContext
    void save() {
        UserDto userDto = new UserDto();
        userDto.setName("orlov");
        userDto.setBirthDay(LocalDate.of(2020, 12, 12));
        userDto.setLogin("undino");
        userDto.setPassword("undino");
        userDto.setPhoneNumber(637916026);
        userDto.setEmail("orlov@mail.sg");
        int id = dataUser.save(userDto);
        assertNotNull(id);
    }

    @Test
    void getOne() {
        assertEquals(1, dataUser.getOne(1).getId());
    }

    @Test
    void hasUser() {
        User user = new User();
        user.setLogin("sdfgdsfg");
        user.setPassword("7777");
        assertEquals(12, dataUser.hasUser(user));
    }

    @Test
    void hasLogin() {
        assertTrue(dataUser.hasLogin("sdfgdsfg"));
    }
}