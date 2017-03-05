package com.tbp.av;


import com.tbp.av.security.AuthProviderService;
import com.tbp.av.security.handler.AjaxAuthenticationFailureHandler;
import com.tbp.av.security.handler.AjaxAuthenticationSuccessHandler;
import com.tbp.av.security.handler.AjaxLogoutSuccessHandler;
import com.tbp.av.security.handler.Http401UnauthorizedEntryPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(value = org.mockito.junit.MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @InjectMocks
    WebSecurityConfig webSecurityConfig;
    @Mock
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Mock
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Mock
    AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    @Mock
    Http401UnauthorizedEntryPoint authenticationEntryPoint;
    @Mock
    AuthProviderService authProvider;
    @Mock
    SecurityProperties security;

    @Test
    public void testConfigure() throws Exception {
        AuthenticationManagerBuilder auth = mock(AuthenticationManagerBuilder.class);
        webSecurityConfig.configure(auth);
        verify(auth).authenticationProvider(authProvider);
    }

    @Test
    public void testPasswordEncoder() {
        assertNotNull(webSecurityConfig.sha());
        assertEquals("SHA-256", webSecurityConfig.sha().getAlgorithm());
    }


}
