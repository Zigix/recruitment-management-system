package com.example.recruitmentanagementsystem.configuration;

import com.example.recruitmentanagementsystem.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private final String secret = "zdtlD3JK56m6wTTgsNFhqzjqP";

    public String generate(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(Map.of("role", user.getRole()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validate(String jwtToken) {
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken);
        return true;
    }

    public String getUsername(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }
}
