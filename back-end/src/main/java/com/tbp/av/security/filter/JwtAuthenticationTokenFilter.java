package com.tbp.av.security.filter;


import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbp.av.model.User;
import com.tbp.av.security.jwt.JwtService;
import com.tbp.av.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        if(authToken != null) {
            authToken = new String(authToken.substring(7).getBytes(), "UTF-8");
            String username = jwtService.getUsername(authToken, request.getRemoteAddr());
            logger.info("checking authentication for user " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userService.findByUsername(username);
                if (jwtService.isValid(authToken, request.getRemoteAddr()) && user != null) {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, user.getPassword(), Arrays.asList(simpleGrantedAuthority));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }
        chain.doFilter(request, response);
    }

}
