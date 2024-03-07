package com.orazov.msbtrust.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;

    // method generateToken Когда придет логин и пароль, проверим, сформируем токен и возвращаем токен клиенту
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifeTime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // подпись токена
                .compact();
    }

    // TODO: Когда клиент присылает токен нам нужно проверить кто этот клиент такой, как его зовут и какие его роли(т.е. разбирать этот токен на куски)


    public String getUsername(String token){ // из токена достаем имя пользователя
        return extractAllClaims(token).getSubject();
    }

    //public List<String> getRoles(String token) { // из токена достаем роль пользователя
        public List<String> getRoles(String token) {
            return extractAllClaims(token).get("roles", List.class);
        }

    // method extractAllClaims - этот метод позволяет нам передать токен и получить Claims(Имя, роли и тд), но это общий метод
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
