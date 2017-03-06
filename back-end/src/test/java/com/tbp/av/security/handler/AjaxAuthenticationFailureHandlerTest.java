package com.tbp.av.security.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import static org.mockito.Mockito.*;

@RunWith(value = MockitoJUnitRunner.class)
public class AjaxAuthenticationFailureHandlerTest {


    @InjectMocks
    AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
    @Mock
    HeaderHandler headerHandler;

    @Test
    public void testOnAuthenticationFailure() throws IOException, ServletException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);

        ajaxAuthenticationFailureHandler.onAuthenticationFailure(request, response, exception);
        verifyZeroInteractions(request, exception);
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
        verify(headerHandler).process(request, response);
    }


}
