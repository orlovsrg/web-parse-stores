package org.itstep.data.parse;

import org.h2.engine.Mode;
import org.itstep.model.ModelEquipment;
import org.itstep.model.LinkProductType;
import org.itstep.model.Store;
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
    private final String GET_ALL_STORE_BY_NAME = "SELECT * FROM store WHERE name = ?";

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

    public Store getStore(String nameStore) {
        return jdbcTemplate.queryForObject(GET_ALL_STORE_BY_NAME, new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {

                return null;
            }
        }, nameStore);
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


    private final String GET_ALL_STORE = "select * from store";
    private final String GET_ALL_PRODUCT_BEGIN = "select * from ";
    private final String GET_ALL_TITLE_PRODUCT_START = "select title, count(*) c from ";
    private final String GET_ALL_TITLE_PRODUCT_END = " group by title order by c desc";
    private final String GET_PRODUCT_BY_NAME_START = "select * from ";
    private final String GET_PRODUCT_BY_NAME_END = " where title = ?";
    private final String GET_PRODUCT_BY_ID_START = "select * from ";
    private final String GET_PRODUCT_BY_ID_END = " where id = ?";

    public List<String> getAllTitle(String typeProduct) {
        return jdbcTemplate.query(GET_ALL_TITLE_PRODUCT_START + typeProduct + GET_ALL_TITLE_PRODUCT_END, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("title");
            }
        });
    }

    public List<Store> getAllStores() {
        return jdbcTemplate.query(GET_ALL_STORE, (rs, rowNum) -> new Store(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("store_url")
        ));
    }

    public List<ModelEquipment> getProductByName(String type, String nameProduct) {
        return jdbcTemplate.query(GET_PRODUCT_BY_NAME_START + type + GET_PRODUCT_BY_NAME_END,
                (rs, rowNum) -> {
                    ModelEquipment modelEquipment = new ModelEquipment();
                    modelEquipment.setId(rs.getInt("id"));
                    modelEquipment.setTitle(rs.getString("title"));
                    modelEquipment.setPrice(rs.getInt("price"));
                    modelEquipment.setImgUrl(rs.getString("img_url"));
                    modelEquipment.setUrl(rs.getString("url"));
                    modelEquipment.setStoreId(rs.getInt("store_id"));
                    return modelEquipment;
                }, nameProduct);
    }

    public ModelEquipment getProductById(String type, int productId) {
        return jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID_START + type + GET_PRODUCT_BY_ID_END,
                (rs, rowNum) -> {
                    ModelEquipment modelEquipment = new ModelEquipment();
                    modelEquipment.setId(rs.getInt("id"));
                    modelEquipment.setTitle(rs.getString("title"));
                    modelEquipment.setPrice(rs.getInt("price"));
                    modelEquipment.setImgUrl(rs.getString("img_url"));
                    modelEquipment.setUrl(rs.getString("url"));
                    modelEquipment.setStoreId(rs.getInt("store_id"));
                    return modelEquipment;
                }, productId);
    }

    public List<ModelEquipment> getProductsByType(String typeProduct) {
        return jdbcTemplate.query(
                GET_ALL_PRODUCT_BEGIN + typeProduct,
                (rs, rowNum) -> {
                    ModelEquipment modelEquipment = new ModelEquipment();
                    modelEquipment.setId(rs.getInt("id"));
                    modelEquipment.setTitle(rs.getString("title"));
                    modelEquipment.setPrice(rs.getInt("price"));
                    modelEquipment.setImgUrl(rs.getString("img_url"));
                    modelEquipment.setUrl(rs.getString("url"));
                    modelEquipment.setStoreId(rs.getInt("store_id"));
                    return modelEquipment;
                });

    }
}