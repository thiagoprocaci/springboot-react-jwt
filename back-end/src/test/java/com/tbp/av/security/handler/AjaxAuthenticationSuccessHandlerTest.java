package com.tbp.av.security.handler;


import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AjaxAuthenticationSuccessHandlerTest {

    @Test
    public void testOnAuthenticationSuccess() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler = new AjaxAuthenticationSuccessHandler();
        ajaxAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        verifyZeroInteractions(request, authentication);
        verify(response).setStatus(HttpServletResponse.SC_OK);

    }

}
