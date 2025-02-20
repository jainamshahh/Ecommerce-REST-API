package com.project.ecommerce.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.ecommerce.model.Users;

import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

    @Value("${jwt.symmetricKey}")
    private String symmetricKey;

    @Value("${jwt.issuer}")
    private String issuer;
    
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;


    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(symmetricKey);
    }

    public String generateJWT(Users user) {
        return JWT.create()
            .withClaim("username", user.getUsername())
            .withClaim("email", user.getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
            .withIssuer(issuer)
            .sign(algorithm);
    }
}
