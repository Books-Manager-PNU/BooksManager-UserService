package com.example.booksManager.security;

import com.example.booksManager.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt-secret}")
    private String SECRET;

    public String generateToken(User user) {
        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .claim("user_id", user.getId())
                .claim("role", user.getRole().name())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }

    public Long getIdFormToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().get("user_id", Long.class);
    }

    public SimpleGrantedAuthority getRoleFromToken(String token) {
        String role = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().get("role", String.class);
        return new SimpleGrantedAuthority(role);
    }
}
