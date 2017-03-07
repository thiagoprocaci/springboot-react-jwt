package com.tbp.av.security.handler;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HeaderHandler {

    static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    static final String OPTIONS = "OPTIONS";
    static final String OK = "OK";
    static final String REQUEST_HEADERS = "Access-Control-Request-Headers";
    static final String STAR = "*"; //* or origin as you prefer
    static final String TRUE = "true";

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader(ALLOW_ORIGIN, STAR);
        response.setHeader(ALLOW_CREDENTIALS, TRUE);
        response.setHeader(ALLOW_HEADERS,  request.getHeader(REQUEST_HEADERS));
        if (request.getMethod().equals(OPTIONS)) {
            response.getWriter().print(OK);
            response.getWriter().flush();
        }
    }
}
