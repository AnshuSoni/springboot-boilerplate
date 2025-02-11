package com.example.demo.services;

import com.example.demo.models.UserPrincipal;
import com.example.demo.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${ecommerce.jwt.secret}")
    private String SECRET;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key())
                .compact();

    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET));
    }

    public String getUsernameFromToken(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public Date getExpiryOfJwtToken(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    public <T> T extractClaims(String jwtToken, Function<Claims, T> claimResolver) {

        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwtToken) {
        return Jwts.parser().verifyWith(key())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        return getExpiryOfJwtToken(token).before(new Date());
    }

    public boolean validateToken(String token, UserPrincipal user) {
        return getUsernameFromToken(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

}
