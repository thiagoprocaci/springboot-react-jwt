package com.tbp.av.security.handler;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HeaderHandler {

    static final String ORIGIN = "Origin";

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String origin = request.getHeader(ORIGIN);
        response.setHeader("Access-Control-Allow-Origin", "*"); //* or origin as u prefer
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        if (request.getMethod().equals("OPTIONS")) {
            response.getWriter().print("OK");
            response.getWriter().flush();
        }

    }
}
