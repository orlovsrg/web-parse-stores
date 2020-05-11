package org.itstep.data.parse;

import org.itstep.model.ModelEquipment;
import org.itstep.model.LinkProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DataEquipment {

    private final String GET_STORE_URL = "SELECT store_url FROM store WHERE name = ?";
    private final String GET_STORE_ID = "SELECT id FROM store WHERE name = ?";
    private final String SAVE_EQUIPMENT_START = "INSERT INTO ";
    private final String SAVE_EQUIPMENT_END = "(title, price, url, img_url, store_id) values (?,?,?,?,?)";
    private final String GET_SELECTOR_KEY = "SELECT product_type, link FROM link_product WHERE store_id = ?";

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataEquipment(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String storeUrl(String storeName) {
        return jdbcTemplate.queryForObject(GET_STORE_URL,
                (rs, rowNum) -> rs.getString("store_url"),
                storeName);
    }

    public int storeId(String storeName) {
//        return jdbcTemplate.query(GET_STORE_ID,
//                ps -> ps.setString(1, storeName),
//                rs -> {
//                    if (rs.next())
//                        return rs.getInt(1);
//                    return null;
//                });

        return jdbcTemplate.queryForObject(GET_STORE_ID, Integer.class, storeName);
    }

    public List<LinkProductType> getSelectorKey(String storeName) {

        int storeId = storeId(storeName);
        return jdbcTemplate.query(GET_SELECTOR_KEY,
                ps -> ps.setInt(1, storeId),
                (rs, rowNum) -> new LinkProductType(storeId, rs.getString("product_type"), rs.getString("link")));


    }

    public void save(String table, ModelEquipment modelEquipment) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE_EQUIPMENT_START + table + SAVE_EQUIPMENT_END);
            ps.setString(1, modelEquipment.getTitle());
            ps.setInt(2, modelEquipment.getPrice());
            ps.setString(3, modelEquipment.getUrl());
            ps.setString(4, modelEquipment.getImgUrl());
            ps.setInt(5, modelEquipment.getStoreId());
            return ps;
        });
    }

    // Show data from DB




}
