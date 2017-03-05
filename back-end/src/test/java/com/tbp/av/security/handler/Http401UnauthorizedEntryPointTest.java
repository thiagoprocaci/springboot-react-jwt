package com.tbp.av.security.handler;


import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class Http401UnauthorizedEntryPointTest {

    @Test
    public void testCommence() throws IOException, ServletException {
        Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint = new Http401UnauthorizedEntryPoint();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException authenticationException = mock(AuthenticationException.class);

        http401UnauthorizedEntryPoint.commence(request, response, authenticationException);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }

}
