package org.itstep.service.authentication;

import org.itstep.data.parse.DataUser;
import org.itstep.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private final DataUser dataUser;

    public CustomUserDetailsService(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("User name: {}", username);

        UserDto user;
        try {
            user = dataUser.getUserDtoByUserLogin(username);
            log.info("User: {}", user);
        } catch (Exception ex) {
            log.error("User not found by name: {}", username);
            throw  new UsernameNotFoundException("Not found user by name: " + username, ex);
        }
        return user;
    }
}
