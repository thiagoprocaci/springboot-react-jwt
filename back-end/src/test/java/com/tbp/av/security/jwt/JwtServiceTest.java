package com.tbp.av.security.jwt;


import com.tbp.av.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class JwtServiceTest {

    public static final String SECRET = "secret";
    public static final String USERNAME = "username";
    JwtService jwtService;

    Date generateValidDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, 1);
        return c.getTime();
    }

    Date generateInValidDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, -1);
        return c.getTime();
    }

    @Before
    public void before() {
        jwtService = new JwtService();
    }

    @Test
    public void testCreateTokenEmptyUsername() {
        assertNull(jwtService.createToken(null, SECRET, generateValidDate()));
        assertNull(jwtService.createToken("", SECRET, generateValidDate()));
        assertNull(jwtService.createToken("  ", SECRET, generateValidDate()));
    }

    @Test
    public void testCreateTokenEmptySecret() {
        assertNull(jwtService.createToken(USERNAME, null, generateValidDate()));
        assertNull(jwtService.createToken(USERNAME, "", generateValidDate()));
        assertNull(jwtService.createToken(USERNAME, "   ", generateValidDate()));
    }

    @Test
    public void testCreateTokenInvalidDate() {
        assertNull(jwtService.createToken(USERNAME, SECRET, null));
        assertNull(jwtService.createToken(USERNAME, SECRET, generateInValidDate()));
    }

    @Test
    public void testCreateTokenSuccess() {
        Date d = generateValidDate();
        String s = jwtService.createToken(USERNAME, SECRET, d);
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertEquals(compactJws, s);
    }

    @Test
    public void testIsValidTokenEmptyToken() {
        assertFalse(jwtService.isValid(null, SECRET));
        assertFalse(jwtService.isValid(" ", SECRET));
    }

    @Test
    public void testIsValidTokenEmptySecret() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertFalse(jwtService.isValid(compactJws, null));
        assertFalse(jwtService.isValid(compactJws, " "));
    }

    @Test
    public void testIsValidTokenExpiredDate() {
        Date d = generateInValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertFalse(jwtService.isValid(compactJws, SECRET));
    }

    @Test
    public void testIsValidTokenWrongSecret() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertFalse(jwtService.isValid(compactJws, "secret2"));
        assertFalse(jwtService.isValid(compactJws, "secrett"));
    }

    @Test
    public void testIsValidTokenSuccess() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertTrue(jwtService.isValid(compactJws, SECRET));
    }

    @Test
    public void testGetUsernameEmptyToken() {
        assertNull(jwtService.getUsername(null, SECRET));
        assertNull(jwtService.getUsername(" ", SECRET));
    }

    @Test
    public void testGetUsernameEmptySecret() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();

        assertNull(jwtService.getUsername(compactJws, null));
        assertNull(jwtService.getUsername(compactJws, " "));
    }

    @Test
    public void testGetUsernameExpired() {
        Date d = generateInValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();

        assertNull(jwtService.getUsername(compactJws, SECRET));
    }

    @Test
    public void testGetUsernameNoSubject() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();

        assertNull(jwtService.getUsername(compactJws, SECRET));
    }

    @Test
    public void testGetUsernameSuccess() {
        Date d = generateValidDate();
        String secret2 = new String(Base64.encodeBase64(SECRET.getBytes()));
        String compactJws = Jwts.builder()
                .setSubject(USERNAME)
                .signWith(SignatureAlgorithm.HS512, secret2)
                .setExpiration(d)
                .compact();
        assertEquals(USERNAME, jwtService.getUsername(compactJws, SECRET));
    }

}
