package org.itstep.data.parse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitWebConfig(locations = {"classpath:spring-db.xml", "classpath:spring-mvc.xml", "classpath:spring-security.xml"})
class DataSubscriptionTest {


    @Autowired
    private DataSubscription dataSubscription;
    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Test
    void getUserIdOfSubscription() {
        List<Integer> list = dataSubscription.getUserIdOfSubscription("phone", 8127);
        System.out.println(list);
        assertTrue(list.size() > 0);
    }
}