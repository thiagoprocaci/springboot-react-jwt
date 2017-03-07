package com.tbp.av.model.factory;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UserFactoryTest {

    @Test
    public void testCreate() {
        UserFactory userFactory = new UserFactory();
        assertNotNull(userFactory.create("username", "pass", "salt", "USER"));
    }

}
