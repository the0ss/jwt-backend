package com.theoss.jwtbackend.configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenProvider {
    
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration}")
    private long jwtExpirationDate;

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        String username=userDetails.getUsername();
        Date currentDate=new Date();
        Date expireDate= new Date(currentDate.getTime() + jwtExpirationDate);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
    }
    private Key key(){
        byte[] bytes=Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(bytes);
    }
    public String getUserName(String token){
        Claims claims=Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        return claims.getSubject();
    }
    public boolean validToken(String token){
        try{
            Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);
            return true;
        }catch(ExpiredJwtException |IllegalArgumentException |SignatureException |MalformedJwtException e){
            throw new RuntimeException(e);
        }
    }
}
