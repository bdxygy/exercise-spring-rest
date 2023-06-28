package io.budisantoso.dev.learnspringsecurity.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private String SECRET_KEY = "8F7DDE36E4CA7B0299C88438C81796B708D49C0C013D6B628E8D2B07C6480F1C8F7DDE36E4CA7B0299C88438C81796B708D49C0C013D6B628E8D2B07C6480F1C";


    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        final Date issuedAt = new Date(System.currentTimeMillis());
//        final Date expiredAt = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
//
//        System.out.println(issuedAt);
//        System.out.println(expiredAt);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week expiration
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        return extractUsername(jwtToken).equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    public <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims userClaims = extractAllClaims(jwtToken);
        return claimsResolver.apply(userClaims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
