package com.tbp.av.security.filter;

import com.tbp.av.model.User;
import com.tbp.av.security.SecurityAppContext;
import com.tbp.av.security.factory.UsernamePasswordAuthenticationTokenFactory;
import com.tbp.av.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.tbp.av.security.filter.JwtAuthenticationTokenFilter.*;
import static org.mockito.Mockito.*;

@RunWith(value = MockitoJUnitRunner.class)
public class JwtAuthenticationTokenFilterTest {

    @InjectMocks
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Mock
    UserService userService;
    @Mock
    UsernamePasswordAuthenticationTokenFactory usernamePasswordAuthenticationTokenFactory;
    @Mock
    SecurityAppContext securityAppContext;

    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain chain;

    @Before
    public void before() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
    }

    @Test
    public void doFilterInternalNullToken() throws IOException, ServletException {
        when(request.getHeader(AUTHORIZATION)).thenReturn(null);
        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
        verifyZeroInteractions(securityAppContext, userService, usernamePasswordAuthenticationTokenFactory );
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalExceptionToken() throws IOException, ServletException {
        when(request.getHeader(AUTHORIZATION)).thenReturn("123");
        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
        verifyZeroInteractions(securityAppContext, userService, usernamePasswordAuthenticationTokenFactory );
        verify(chain).doFilter(request, response);
    }


    @Test
    public void doFilterInternalAuthenticationNotNull() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);

        SecurityContext context = mock(SecurityContext.class);
        when(securityAppContext.getContext()).thenReturn(context);
        Authentication authentication = mock(Authentication.class);
        when(context.getAuthentication()).thenReturn(authentication);

        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
        verifyZeroInteractions(userService, usernamePasswordAuthenticationTokenFactory );
        verify(chain).doFilter(request, response);
        verify(context, never()).setAuthentication(any(Authentication.class));
    }

    @Test
    public void doFilterInternalAuthenticationNullUser() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);
        when(request.getRemoteAddr()).thenReturn("localhost");

        SecurityContext context = mock(SecurityContext.class);
        when(securityAppContext.getContext()).thenReturn(context);
        when(context.getAuthentication()).thenReturn(null);

        when(userService.validateUser("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg", request.getRemoteAddr())).thenReturn(null);

        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);
        verifyZeroInteractions(usernamePasswordAuthenticationTokenFactory);
        verify(context, never()).setAuthentication(any(Authentication.class));
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterInternalAuthenticationSuccess() throws ServletException, IOException {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg";
        when(request.getHeader(AUTHORIZATION)).thenReturn(token);
        when(request.getRemoteAddr()).thenReturn("localhost");

        SecurityContext context = mock(SecurityContext.class);
        when(securityAppContext.getContext()).thenReturn(context);
        when(context.getAuthentication()).thenReturn(null);

        User u = new User("username", "pass", "salt", "role");
        when(userService.validateUser("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.GE2q1gX6T-mcjf0xmIlGru1gzu-PQF1leFK4U3Kphj8ZLpQG3Rn8qyLLO38ilyvP2u03Ft7bEBAJqRS-86WXCg", request.getRemoteAddr())).thenReturn(u);

        Authentication authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(usernamePasswordAuthenticationTokenFactory.create(u)).thenReturn((UsernamePasswordAuthenticationToken) authentication);

        jwtAuthenticationTokenFilter.doFilterInternal(request, response, chain);

        verify(context).setAuthentication(authentication);
        verify(chain).doFilter(request, response);
    }
}
