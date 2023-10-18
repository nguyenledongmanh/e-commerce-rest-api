package com.ecommerceproject.ecommercerestapi.security;

import com.ecommerceproject.ecommercerestapi.exception.ECommerceAPIException;
import com.ecommerceproject.ecommercerestapi.model.entity.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;


    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        Collection<?> authorities = authentication.getAuthorities();
        boolean isAdmin = false;
        for (Object role : authorities) {
            if (((SimpleGrantedAuthority) role).getAuthority().contains("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        return Jwts.builder()
                   .setSubject(username)
                   .setIssuedAt(new Date())
                   .setExpiration(expireDate)
                   .claim("isAdmin", isAdmin)
                   .signWith(key())
                   .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from JWT
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }

    // validate jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token);
        } catch (MalformedJwtException ex) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        } catch (Exception e) {
            throw new ECommerceAPIException(HttpStatus.BAD_REQUEST, "Some exceptions happen");
        }
        return true;
    }
}
