package com.employee.management.system.www.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Validation {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean validateUserNameAndToken(UserDetails userDetails, String token) {
        String userName = extractUserName(token);
        boolean hasTokenExpired = isTokenExpired(token);
        return userName.equals(userDetails.getUsername()) && hasTokenExpired;
    }

    public String extractUserName(String token) {
        return extractClaimes(token).getSubject();

    }

    private Claims extractClaimes(String token) {
        return Jwts.parser().setSigningKey(jwtUtil.getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractClaimes(token).getExpiration();
        return expirationDate.after(new Date());
    }
}
