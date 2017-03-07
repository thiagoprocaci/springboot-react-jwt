package com.tbp.av.model.factory;

import com.tbp.av.model.User;
import org.springframework.stereotype.Component;



@Component
public class UserFactory {

    public User create(String username, String password, String salt, String role) {
        return new User(username, password, salt, role);
    }

}
