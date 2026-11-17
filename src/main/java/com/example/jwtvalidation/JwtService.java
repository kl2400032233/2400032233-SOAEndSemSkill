
package com.example.jwtvalidation;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET =
        "MySecretKeyForJWTValidation123456789";

    private Key getKey() {
        return Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Generate JWT Token
    public String generateToken(String username) {

        return Jwts.builder()
            .subject(username)
            .claim("role", "Admin")
            .issuedAt(new Date())
            .expiration(
                new Date(System.currentTimeMillis() + 3600000)
            )
            .signWith(getKey())
            .compact();
    }

    // Validate JWT Token and extract claims
    public Claims validateToken(String token) {

        return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
