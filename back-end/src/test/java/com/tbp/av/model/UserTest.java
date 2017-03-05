package com.tbp.av.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testCreateEmptyConstructor() {
        User u = new User();
        u.id = 1;
        u.password = "a";
        u.username = "b";
        u.salt = "c";

        assertEquals(u.id, u.getId());
        assertEquals(u.password, u.getPassword());
        assertEquals(u.username, u.getUsername());
        assertEquals(u.salt, u.getSalt());
    }

    @Test
    public void testCreateNonEmptyConstructor() {
        User u = new User("username", "pass", "salt");
        assertEquals("pass", u.getPassword());
        assertEquals("username", u.getUsername());
        assertEquals("salt", u.getSalt());
    }


}
