package com.tbp.av.security;

import com.tbp.av.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class AuthProviderService implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthProviderService.class);

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        // TODO colocar na base 64
        String password = authentication.getCredentials().toString();
        LOGGER.info("Doing login " + login);
        if(userService.isLoginValid(login, password)) {
            LOGGER.info("Login successful " + login);
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
            return new UsernamePasswordAuthenticationToken(login, password, Arrays.asList(simpleGrantedAuthority));
        }
        throw new UsernameNotFoundException("Not valid login/password");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
