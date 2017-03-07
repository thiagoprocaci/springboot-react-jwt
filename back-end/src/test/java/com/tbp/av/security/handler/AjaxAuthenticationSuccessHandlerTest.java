package com.tbp.av.security.handler;



import com.tbp.av.model.User;
import com.tbp.av.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@RunWith(value = MockitoJUnitRunner.class)
public class AjaxAuthenticationSuccessHandlerTest {

    @InjectMocks
    AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    @Mock
    HeaderHandler headerHandler;
    @Mock
    UserService userService;

    @Test
    public void testOnAuthenticationSuccess() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);

        when(authentication.getName()).thenReturn("name");
        when(request.getRemoteAddr()).thenReturn("localhost");

        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        String token = "token";
        User u = new User("usernma", "pass", "salt", "role");
        u.setToken("token");

        when(userService.createUserToken(authentication.getName(), request.getRemoteAddr())).thenReturn(u);

        ajaxAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(headerHandler).process(request, response);
        verify(userService).createUserToken(authentication.getName(), request.getRemoteAddr()) ;
        verify(printWriter).print("{ \"token\" : \"" + u.getToken() + "\"}");
    }

}
