package com.tbp.av.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testCreateEmptyConstructor() {
        User u = new User();
        u.id = 1;
        u.password = "a";
        u.username = "b";
        u.salt = "c";
        u.token = "d";
        u.setToken("e");

        assertEquals(u.id, u.getId());
        assertEquals(u.password, u.getPassword());
        assertEquals(u.username, u.getUsername());
        assertEquals(u.salt, u.getSalt());
        assertEquals(u.token, u.getToken());
        assertEquals(u.role, u.getRole());
    }

    @Test
    public void testCreateNonEmptyConstructor() {
        User u = new User("username", "pass", "salt", "USER");
        assertEquals("pass", u.getPassword());
        assertEquals("username", u.getUsername());
        assertEquals("salt", u.getSalt());
        assertEquals(u.getRole(), "USER");
    }


}
