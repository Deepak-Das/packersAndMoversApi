package com.example.packersandmoversapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    final String SECRET_KEY = "AMQCu/YatyMuaeUCe2wjCutPGrYl6H07kTQNEmH2JqLBcskScq8f2DjdSHPnw+KzRBXlDInA8MAFYNKJaz6t5N5XEljvqSnV48D+rbymAsyk0/WWzo0Yk2EyzZ11+ndSKObtFQ7qYXlkJ5+ET4yGNoxq8zRo6bkC4W+PprYKmYWCPhuRCDp0H3Zk9Sp3Hd+s3jQTFQ3aE8otU7BIb0IEhubQLMGD8D1uonE6YdicyxbfaJWZH2qtkz4llNXr+1KKzZkBtW7gIc3HfcvNRsL1FRhi79OrmXGjStaIeni3CrCITsVkay1m9NzAHErbXQ/8FMhMJLdXdbhsZ3UZ+85iwmT77nlugC4NBx1v0o67URQ=\n";

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public Boolean isTokenValid(String token,UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    private  <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }




    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();



    }

    private SecretKey getSignKey() {
        byte[] bytesKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytesKey);
    }
}
