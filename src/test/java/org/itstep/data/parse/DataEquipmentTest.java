package org.itstep.data.parse;

import org.itstep.model.ModelEquipment;
import org.itstep.model.Store;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DirtiesContext
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
@SpringJUnitWebConfig(locations = {"classpath:spring-db.xml", "classpath:spring-mvc.xml", "classpath:spring-security.xml"})
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

    @Test
    void getAllStore() {
        assertTrue(dataEquipment.getAllStores().contains("foxtrot"));
        assertTrue(dataEquipment.getAllStores().contains("comfy"));
    }

    @Test
    void getProductsByType() {
        List<ModelEquipment> modelEquipmentListStoreIdOne = dataEquipment.getProductsByType("phone");
        List<ModelEquipment> modelEquipmentListStoreIdTwo = dataEquipment.getProductsByType("phone");
        modelEquipmentListStoreIdOne.forEach(System.out::println);
        modelEquipmentListStoreIdTwo.forEach(System.out::println);
        assertTrue(modelEquipmentListStoreIdOne.size() > 0
                && modelEquipmentListStoreIdTwo.size() > 0);
    }

    @Test
    void getAllStores() {
       List<Store> storeList = dataEquipment.getAllStores();
       storeList.forEach(System.out::println);
       assertTrue(storeList.size() > 0);
    }
}