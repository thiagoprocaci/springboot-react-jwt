package com.tbp.av.security;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAppContext {

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

}
