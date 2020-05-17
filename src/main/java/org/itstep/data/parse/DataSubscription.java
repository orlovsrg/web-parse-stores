package org.itstep.data.parse;

import org.itstep.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DataSubscription {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataSubscription(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String ADD_SUBSCRIPTION = "insert into user_subscription(user_id, product_id, product_type) values (?, ?, ?)";
    private final String GET_USER_SUBSCRIPTION = "select * from user_subscription where user_id = ?";

    public void addSubscription(Subscription subscription) {
        jdbcTemplate.update(ADD_SUBSCRIPTION,
                subscription.getUserId(), subscription.getProductId(), subscription.getProductType());
    }

    public List<Subscription> getUserSubscription(int userId){
        return jdbcTemplate.query(GET_USER_SUBSCRIPTION, (rs, rowNum) -> new Subscription(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"), rs.getString("product_type")), userId);
    }
}
