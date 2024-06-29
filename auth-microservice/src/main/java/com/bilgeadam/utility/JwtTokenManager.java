package com.bilgeadam.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${jwt.secret}")
    String secretKey;
    @Value("${jwt.issuer}")
    String issuer;
    @Value("${jwt.expiration}")
    int expiration;


    public Optional<String> createToken(Long id){

        String token = null;
        try {
            token= JWT.create().withIssuer(issuer).withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiration*1000))
                    .withClaim("authId",id).sign(Algorithm.HMAC512(secretKey));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(token);
    }



    public Optional<String> createToken(Long id,String role){

        String token = null;
        try {
            token= JWT.create().withIssuer(issuer).withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiration*1000))
                    .withClaim("authId",id)
                    .withClaim("role",role)
                    .sign(Algorithm.HMAC512(secretKey));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(token);
    }
    public Optional<String> createToken(String role){

        String token = null;
        try {
            token= JWT.create().withIssuer(issuer).withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiration*1000))
                    .withClaim("role",role)
                    .sign(Algorithm.HMAC512(secretKey));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(token);
    }

    public Optional<String> getRoleFromToken(String token){
        try {
            Optional<DecodedJWT> jwt = verifyToken(token);
            if (jwt.isEmpty()) {
                return Optional.empty();
            }
            String role = jwt.get().getClaim("role").asString();
            return Optional.of(role);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return  Optional.empty();
        }
    }
    public Optional<Long> getAuthIdFromToken(String token){
        try {
            Optional<DecodedJWT> jwt = verifyToken(token);
            if (jwt.isEmpty()) {
                return Optional.empty();
            }
            Long authId = jwt.get().getClaim("authId").asLong();
            return Optional.of(authId);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return  Optional.empty();
        }
    }

    public Optional<DecodedJWT> verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT jwt = verifier.verify(token);
        return Optional.ofNullable(jwt);
    }
}
