package com.tbp.av.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class JwtService {

    static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    public String createToken(String username, String secret, Date expireAt) {
       if(StringUtils.hasText(username) && StringUtils.hasText(secret) && expireAt != null && expireAt.after(new Date()) ) {
           String secret2 = new String(Base64.encodeBase64(secret.getBytes()));
           String compactJws = Jwts.builder()
                    .setSubject(username)
                    .signWith(SignatureAlgorithm.HS512, secret2)
                    .setExpiration(expireAt)
                    .compact();
            return compactJws;
        }
       return null;
    }

    public boolean isValid(String token, String secret) {
        if(StringUtils.hasText(token) && StringUtils.hasText(secret)) {
            try {
                String secret2 = new String(Base64.encodeBase64(secret.getBytes()));
                Jwts.parser().setSigningKey(secret2).parseClaimsJws(token);
                return true;
            } catch (JwtException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }

    public String getUsername(String token, String secret) {
        if(StringUtils.hasText(token) && StringUtils.hasText(secret)) {
            try {
                String secret2 = new String(Base64.encodeBase64(secret.getBytes()));
                return Jwts.parser().setSigningKey(secret2).parseClaimsJws(token).getBody().getSubject();
            }  catch (JwtException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }


}
