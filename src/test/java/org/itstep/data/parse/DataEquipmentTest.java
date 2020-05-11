package org.itstep.data.parse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DirtiesContext
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
@SpringJUnitWebConfig(locations = {"classpath:spring-db.xml", "classpath:spring-mvc.xml"})
class DataEquipmentTest {
    private final Logger log = LoggerFactory.getLogger(DataEquipmentTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataEquipment dataEquipment;

    @Test
    void storeId() {
        assertNotNull(dataEquipment);
        assertEquals(0, dataEquipment.storeId("foxtrot"));
    }

}