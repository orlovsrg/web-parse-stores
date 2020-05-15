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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DataUser {
    private final Logger log = LoggerFactory.getLogger(DataUser.class);
    private final String SAVE_USER = "insert into user_table(name, birth_day, login, password, phone_number, email) values(?,?,?,?,?,?);";
    private final String GET_USER = "select * from user_table where id = ?";
    private final String GET_USER_BY_LOGIN = "select * from user_table where login = ?";
    private final String HAS_USER = "select id from user_table where login = ? and password = ?";
    private final String HAS_LOGIN = "select id from user_table where login = ?";
    private final String GET_ROLE_DEFAULT = "select id from user_role where role = 'ROLE_USER'";
    private final String SAVE_USER_ROLE = "insert into user_role_table(user_id, role_id)  values (?,?)";
    private final String GET_ALL_ROLES_USER = "select role\n" +
            "from user_role_table\n" +
            "join user_role ur on user_role_table.role_id = ur.id\n" +
            "join user_table ut on user_role_table.user_id = ut.id where user_id = ?;";
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
        addRole(Objects.requireNonNull(id.getKey()).intValue());
        return Objects.requireNonNull(id.getKey()).intValue();
    }

    public void addRole(int userId) {
        int roleId = jdbcTemplate.queryForObject(GET_ROLE_DEFAULT, Integer.class);
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(SAVE_USER_ROLE);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            return ps;
        });
    }

//    public boolean addRole(int userId, int roleId){
//        roleId = Objects.
//        return
//    }

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

    public UserDto getUserDtoByUserLogin(String login) {

        return jdbcTemplate.queryForObject(GET_USER_BY_LOGIN, new Object[]{login}, (rs, rowNum) -> {
            UserDto userResult = new UserDto();
            userResult.setId(rs.getInt("id"));
            userResult.setName(rs.getString("name"));
            userResult.setBirthDay(LocalDate.parse(rs.getString("birth_day")));
            userResult.setLogin(rs.getString("login"));
            userResult.setPassword(rs.getString("password"));
            userResult.setPhoneNumber(rs.getInt("phone_number"));
            userResult.setEmail(rs.getString("email"));
            userResult.setRole(getSetRole(userResult.getId()));
            return userResult;
        });

    }

    public Set<String> getSetRole(int userId) {
        List<String> listRole = jdbcTemplate.query(GET_ALL_ROLES_USER, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("role");
            }
        }, userId);
        return new HashSet<>(listRole);

    }

    public boolean hasLogin(String login) {
        int id = 0;
        try {
            id = jdbcTemplate.queryForObject(HAS_LOGIN, Integer.class, login);
        } catch (EmptyResultDataAccessException ex) {
            log.info("new user");
        }
        return id > 0;
    }

    public int hasUser(User user) {

        int id = 0;
        try {
            id = jdbcTemplate.queryForObject(HAS_USER, Integer.class, user.getLogin(), user.getPassword());
        } catch (EmptyResultDataAccessException ex) {
            log.info("User not found");
        }
        return id;
    }

}
