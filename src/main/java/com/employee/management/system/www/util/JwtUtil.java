package com.employee.management.system.www.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String SECRET_KEY;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateJwtToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return claim(claims, userName);
    }

    public String claim(Map<String, Object> claim, String userName) {
        return Jwts.builder()
                .claims(claim)
                .subject(userName)
                .header().empty().add("type", "JWT")
                .and()
                .signWith(getSecretKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .compact();
    }

}
