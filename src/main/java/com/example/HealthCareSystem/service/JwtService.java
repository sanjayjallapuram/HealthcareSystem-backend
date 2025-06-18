//package com.example.HealthCareSystem.service;
//
//import com.example.HealthCareSystem.entity.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    private static final String SECRET_KEY = "TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V"; // Use a strong key in production!
//
//     public Key key;
//
//    @PostConstruct
//    public void init(){
////        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
//        this.key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public String generateToken(String username) {
//        return generateToken(new HashMap<>(), username);
//    }
//
//    public String generateToken(Map<String, Object> Claims, String username) {
//        return Jwts
//                .builder()
//                .setClaims(Claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
//                .signWith(key,SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token, String username) {
//        final String tokenUsername = extractUsername(token);
//        return (tokenUsername.equals(username)) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
////    private Key SignInKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
////        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
////    }
//
//    public Key getKey(){
//        return key;
//    }
//
//}
