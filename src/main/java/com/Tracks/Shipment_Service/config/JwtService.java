package com.Tracks.Shipment_Service.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {
    private static final String SECRET =
            "very-secret-key-that-is-at-least-256-bits-long-for-jwt-hmac-sha-algorithm-requirements";

    public String generatetoken(UserDetails user){

//taken that user
        //bild token

            return Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(
                            new Date(System.currentTimeMillis()
                                    + 1000 * 60 * 60)
                    )
                    .signWith(
                            Keys.hmacShaKeyFor(
                                    SECRET.getBytes()
                            ),
                            SignatureAlgorithm.HS256
                    )
                    .compact();
        }

        //see if it legal by decoding token and taking the username
        public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}
