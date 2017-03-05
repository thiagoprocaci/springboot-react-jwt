package com.tbp.av.security.handler;

import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import static org.mockito.Mockito.*;


public class AjaxAuthenticationFailureHandlerTest {

    @Test
    public void testOnAuthenticationFailure() throws IOException, ServletException {
        AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler = new AjaxAuthenticationFailureHandler();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);

        ajaxAuthenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        verifyZeroInteractions(request, exception);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");

    }


}
