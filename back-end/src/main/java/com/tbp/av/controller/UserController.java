package com.tbp.av.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {

    @RequestMapping("/user")
    public ResponseEntity user(HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        if (remoteUser == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        System.out.println(remoteUser);
        return null;
    }

}
