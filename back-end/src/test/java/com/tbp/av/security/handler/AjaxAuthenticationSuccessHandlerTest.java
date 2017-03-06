package com.tbp.av.security.handler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(value = MockitoJUnitRunner.class)
public class AjaxAuthenticationSuccessHandlerTest {

    @InjectMocks
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Mock
    HeaderHandler headerHandler;

    @Test
    public void testOnAuthenticationSuccess() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);


        ajaxAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        verifyZeroInteractions(request, authentication);
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(headerHandler).process(request, response);
    }

}
