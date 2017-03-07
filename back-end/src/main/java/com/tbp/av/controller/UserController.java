package com.tbp.av.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    static final String USERNAME = "username";
    static final String AUTHORIZATIONS = "permissions";

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public HttpEntity<Map> user(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<String,Object>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Boolean> authorizations = new HashMap();
        // TODO tem regras aqui que deveriam ir para o service
        for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
            authorizations.put(grantedAuthority.getAuthority(), Boolean.TRUE);
        }
        result.put(AUTHORIZATIONS, authorizations);
        String username = (String) auth.getPrincipal();
        result.put(USERNAME, username);
        if("anonymousUser".equals(username)) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new HttpEntity(result);
    }

}
