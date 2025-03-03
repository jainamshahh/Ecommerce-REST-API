package com.project.ecommerce.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;

@SpringBootTest
public class JWTServiceTest {

    private JWTService jwtService;

    private UsersRepository userRepository;

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


}
