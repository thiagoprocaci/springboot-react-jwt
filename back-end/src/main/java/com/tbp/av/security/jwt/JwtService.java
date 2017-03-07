package com.tbp.av.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    // TODO melhorar esta classe... fazer teste

    public String createToken(String username, String secret) {
        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }

    public boolean isValid(String token, String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            //OK, we can trust this JWT
            return true;
        } catch (SignatureException e) {
            //don't trust the JWT!
            return false;
        }
    }

    public String getUsername(String token, String secret) {
       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
