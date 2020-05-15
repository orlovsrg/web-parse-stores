package org.itstep.data.parse;

import org.h2.engine.Mode;
import org.itstep.model.ModelEquipment;
import org.itstep.model.LinkProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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


    private final String GET_ALL_STORE = "select name from store";
    private final String GET_ALL_PRODUCT_START = "select * from ";
    private final String GET_ALL_PRODUCT_END = " where store_id = ?";

    public List<String> getAllStores() {
        return jdbcTemplate.queryForList(GET_ALL_STORE, String.class);
    }

    public List<ModelEquipment> getProductsByType(String typeProduct, int storeId) {
        return jdbcTemplate.query(
                GET_ALL_PRODUCT_START + typeProduct + GET_ALL_PRODUCT_END,
                new Object[]{storeId},
                (rs, rowNum) -> {
                    ModelEquipment modelEquipment1 = new ModelEquipment();
                    modelEquipment1.setId(rs.getInt("id"));
                    modelEquipment1.setTitle(rs.getString("title"));
                    modelEquipment1.setPrice(rs.getInt("price"));
                    modelEquipment1.setImgUrl(rs.getString("img_url"));
                    modelEquipment1.setUrl(rs.getString("url"));
                    modelEquipment1.setStoreId(rs.getInt("store_id"));
                    return modelEquipment1;
                });

    }
}