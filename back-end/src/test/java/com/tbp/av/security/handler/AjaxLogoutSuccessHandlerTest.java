package com.tbp.av.security.handler;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AjaxLogoutSuccessHandlerTest {

    AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    HttpServletRequest request;
    HttpServletResponse response;
    Authentication authentication;
    HttpSession httpSession;
    HeaderHandler headerHandler;

    @Before
    public void before() {
        ajaxLogoutSuccessHandler = new AjaxLogoutSuccessHandler();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        authentication = mock(Authentication.class);
        headerHandler = mock(HeaderHandler.class);
        ajaxLogoutSuccessHandler.headerHandler = headerHandler;
        when(authentication.getDetails()).thenReturn("Details");
        httpSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void testOnLogoutSuccess() throws IOException, ServletException {
        ajaxLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verify(request).getSession();
        verify(httpSession).invalidate();
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(headerHandler).process(request, response);
        verify(response, never()).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }


    @Test
    public void testOnLogoutSuccessNullAuthenticationDetails() throws IOException, ServletException {
        when(authentication.getDetails()).thenReturn(null);
        ajaxLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verify(request, never()).getSession();
        verify(httpSession, never()).invalidate();
        verify(response, never()).setStatus(HttpServletResponse.SC_OK);
        verify(response, never()).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        verifyZeroInteractions(request, response);
    }

    @Test
    public void testOnLogoutSuccessNullAuthentication() throws IOException, ServletException {
        ajaxLogoutSuccessHandler.onLogoutSuccess(request, response, null);
        verify(request, never()).getSession();
        verify(httpSession, never()).invalidate();
        verify(response, never()).setStatus(HttpServletResponse.SC_OK);
        verify(response, never()).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        verifyZeroInteractions(request, response);
    }

    @Test
    public void testOnLogoutSuccessException() throws IOException, ServletException {
        doThrow(new NullPointerException()).when(request).getSession();
        ajaxLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);
        verify(httpSession, never()).invalidate();
        verify(response, never()).setStatus(HttpServletResponse.SC_OK);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

}
