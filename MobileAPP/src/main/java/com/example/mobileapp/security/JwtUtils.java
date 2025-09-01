package com.example.mobileapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    private final long accessExpirationMs;

    private final long refreshExpirationMs;
    private final SecretKey jwtSecretKey;

    public JwtUtils(@Value("${jwt.access.expiration}")long accessExpirationMs, @Value("${jwt.refresh.expiration}")long refreshExpirationMs, SecretKey jwtSecretKey) {
        this.accessExpirationMs = accessExpirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
        this.jwtSecretKey = jwtSecretKey;
    }

    public String generateAccessToken(UserDetails user) {


        return buildToken(user,accessExpirationMs);
    }

    public String generateRefreshToken(UserDetails user) {
        return buildToken(user, refreshExpirationMs);
    }

    private String buildToken(UserDetails user, long expiration) {
//        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("auth", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
