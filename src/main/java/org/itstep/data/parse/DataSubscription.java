package org.itstep.data.parse;

import org.itstep.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
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
    private final String DELETE_SUBSCRIPTION = "delete from user_subscription where id = ?";
    private final String GET_USER_ID = "select user_id from user_subscription where product_type = ? and product_id = ?";
    private final String GET_SUB = "select * from user_subscription where user_id = ? and product_id = ?";

    public void addSubscription(Subscription subscription) {
        jdbcTemplate.update(ADD_SUBSCRIPTION,
                subscription.getUserId(), subscription.getProductId(), subscription.getProductType());
    }

    public List<Subscription> getUserSubscription(int userId) {
        return jdbcTemplate.query(GET_USER_SUBSCRIPTION, (rs, rowNum) -> new Subscription(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"), rs.getString("product_type")), userId);
    }

    public Subscription getSubscription(int userId, int productId) {
        return jdbcTemplate.queryForObject(GET_SUB, (rs, rowNum) -> {
            Subscription subscription = new Subscription();
            subscription.setId(rs.getInt("id"));
            subscription.setUserId(rs.getInt("user_id"));
            subscription.setProductId(rs.getInt("product_id"));
            return subscription;
        }, userId, productId);
    }

    public void delete(Subscription subscription) {
        jdbcTemplate.update(DELETE_SUBSCRIPTION, subscription.getId());
    }

    public List<Integer> getUserIdOfSubscription(String type,  int productId){
       return jdbcTemplate.query(GET_USER_ID, new Object[]{type, productId}, (rs, rowNum) -> rs.getInt("user_id"));
    }

}
