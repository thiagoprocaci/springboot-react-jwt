package com.tbp.av.security;

import com.tbp.av.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(userService.isLoginValid(login, password)) {
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
