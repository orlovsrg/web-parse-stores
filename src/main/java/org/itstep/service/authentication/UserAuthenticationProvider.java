package org.itstep.service.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(UserAuthenticationProvider.class);

    @Autowired
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAuthenticationProvider(CustomUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String linePassword = authentication.getCredentials().toString();

        log.info("User authentication provider username: {}, linePassword: {}", username, linePassword);

        UserDetails userDetails;

        try{
            userDetails = userDetailsService.loadUserByUsername(username);
            log.info("User authentication provider username: {}, linePassword: {}", username, linePassword);
            log.info("!bCryptPasswordEncoder: {}", !bCryptPasswordEncoder.matches(linePassword, userDetails.getPassword()));
            if (!bCryptPasswordEncoder.matches(linePassword, userDetails.getPassword())){
                throw new BadCredentialsException("Bad credentials");
            }

        } catch (Exception ex){
            throw new BadCredentialsException("User not found");
        }
        log.warn("UsernamePasswordAuthenticationToken get is: " +
                "userDetails= {}, " +
                "userDetails.getPassword()= {}, " +
                "getAuthorities()= {}", userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.error("TEST SUPPORT: {}", UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
