package com.bilgeadam.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ElasticManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtTokenManager {

    @Value("${jwt.secret}")
    String secretKey;
    @Value("${jwt.issuer}")
    String issuer;


    public Optional<String> getRoleFromToken(String token){
           DecodedJWT jwt = verifyToken(token);
            String role = jwt.getClaim("role").asString();
            return Optional.of(role);

    }
    public Optional<Long> getAuthIdFromToken(String token){
            DecodedJWT jwt = verifyToken(token);
            Long authId = jwt.getClaim("authId").asLong();
            return Optional.of(authId);
    }

    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT jwt = verifier.verify(token);
        return Optional.ofNullable(jwt).orElseThrow(()->new ElasticManagerException(ErrorType.INVALID_TOKEN));
    }
}
