package org.itstep.data.parse;

import org.itstep.dto.UserDto;
import org.itstep.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class DataUser {
    private final Logger log = LoggerFactory.getLogger(DataUser.class);
    private final String SAVE_USER = "insert into user_table(name, birth_day, login, password, phone_number, email) values(?,?,?,?,?,?);";
    private final String GET_USER = "select * from user_table where id = ?";
    private final String HAS_USER = "select id from user_table where login = ? and password = ?";
    private final String HAS_LOGIN = "select id from user_table where login = ?";
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(UserDto userDto) {
        KeyHolder id = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDto.getName());
            ps.setString(2, userDto.getBirthDay().toString());
            ps.setString(3, userDto.getLogin());
            ps.setString(4, userDto.getPassword());
            ps.setInt(5, userDto.getPhoneNumber());
            ps.setString(6, userDto.getEmail());
            return ps;
        }, id);
        return id.getKey().intValue();
    }

    public User getOne(int id) {
        User user = new User();
        UserDto userDto = jdbcTemplate.queryForObject(GET_USER, new Object[]{id}, (rs, rowNum) -> {
            UserDto userResult = new UserDto();
            userResult.setId(rs.getInt("id"));
            userResult.setName(rs.getString("name"));
            userResult.setBirthDay(LocalDate.parse(rs.getString("birth_day")));
            userResult.setLogin(rs.getString("login"));
            userResult.setPassword(rs.getString("password"));
            userResult.setPhoneNumber(rs.getInt("phone_number"));
            userResult.setEmail(rs.getString("email"));
            return userResult;
        });
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setBirthDay(userDto.getBirthDay());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public boolean hasLogin(String login) {
        int id = 0;
        try {
           id  = jdbcTemplate.queryForObject(HAS_LOGIN, Integer.class, login);
        } catch (EmptyResultDataAccessException ex){
            log.info("new user");
        }
        return id > 0;
    }

    public int hasUser(User user) {
        int id = 0;
        try {
            id = jdbcTemplate.queryForObject(HAS_USER, Integer.class, user.getLogin(), user.getPassword());
        } catch (EmptyResultDataAccessException ex){
            log.info("User not found");
        }
        return id;

    }

}
