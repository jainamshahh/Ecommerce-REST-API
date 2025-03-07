package com.project.ecommerce.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;

@SpringBootTest
@AutoConfigureMockMvc // not required for this class , added due to bug in junit
public class JWTServiceTest {

    private JWTService jwtService;

    private UsersRepository userRepository;

    @Value("${jwt.symmetricKey}")
    private String symmetricKey;

    public JWTServiceTest(@Autowired JWTService jwtService,@Autowired UsersRepository userRepository){
        this.jwtService=jwtService;
        this.userRepository=userRepository;
    }

    @Test
    public void testAuthTokenReturnsUsername() {
        Users user = userRepository.findByUsername("UserA").get();
        String token = jwtService.generateJWT(user);
        Assertions.assertEquals(user.getUsername(), jwtService.getUsername(token), "Token for auth should contain users username.");
    }

    @Test
    public void testInvalidJWT() {
        String token =
            JWT.create().withClaim("USERNAME", "UserA").sign(Algorithm.HMAC256(
            "NotTheRealSecret"));
        Assertions.assertThrows(SignatureVerificationException.class,
            () -> jwtService.getUsername(token));
    }

    @Test
    public void testJWTCorrectlySignedNoIssuer() {
        String token =
            JWT.create().withClaim("USERNAME", "UserA")
                .sign(Algorithm.HMAC256(symmetricKey));
        Assertions.assertThrows(MissingClaimException.class,
            () -> jwtService.getUsername(token));
    }


}
