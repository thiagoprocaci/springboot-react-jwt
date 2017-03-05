package com.tbp.av.service;

import com.tbp.av.model.User;
import com.tbp.av.model.factory.UserFactory;
import com.tbp.av.repository.UserRepository;
import com.tbp.av.support.StringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShaPasswordEncoder shaPasswordEncoder;
    @Autowired
    StringSupport stringSupport;
    @Autowired
    UserFactory userFactory;


    public void create(String username, String password) {
        String salt = stringSupport.generate();
        User u = userFactory.create(username, shaPasswordEncoder.encodePassword(password, salt), salt);
        userRepository.save(u);
    }

    public boolean isLoginValid(String username, String password)  {
        if(!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return false;
        }
        User u = userRepository.findByUsername(username);
        if(u == null) {
            return false;
        }
        if(!u.getPassword().equals(shaPasswordEncoder.encodePassword(password, u.getSalt()))) {
            return false;
        }
        return true;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
