package com.tbp.av.database;

import com.tbp.av.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class InitDatabase {


    @Autowired
    public InitDatabase(UserService userService) {
        userService.create("admin", "admin", "USER");
        userService.create("tomcat", "tomcat", "USER");
    }


}
