package com.project.ecommerce.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.ecommerce.api.model.LoginBody;
import com.project.ecommerce.api.model.UserRegisterationBody;
import com.project.ecommerce.exception.UserAlreadyExists;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc // not required for this class , added due to bug in junit
public class UserServiceTest {

    
    private UserService userService;

    public UserServiceTest(@Autowired UserService userService){
        this.userService=userService;
    }
    
    @Test
    @Transactional
    public void testRegisterUser() {
        UserRegisterationBody body = new UserRegisterationBody();
        body.setUsername("UserA");
        body.setEmail("UserServiceTest$testRegisterUser@junit.com");
        body.setFirstName("FirstName");
        body.setLastName("LastName");
        body.setPassword("MySecretPassword123");
        Assertions.assertThrows(UserAlreadyExists.class,
            () -> userService.registerUser(body), "Username should already be in use.");
        body.setUsername("UserServiceTest$testRegisterUser");
        body.setEmail("UserA@junit.com");
        Assertions.assertThrows(UserAlreadyExists.class,
            () -> userService.registerUser(body), "Email should already be in use.");
        body.setEmail("UserServiceTest$testRegisterUser@junit.com");
        Assertions.assertDoesNotThrow(() -> userService.registerUser(body),
            "User should register successfully.");
        
    }

    @Test
    @Transactional
    public void testLoginUser() {
        LoginBody body = new LoginBody();
        body.setUsername("UserA-NotExists");
        body.setPassword("PasswordA123-BadPassword");
        Assertions.assertNull(userService.loginUser(body), "The user should not exist.");
        body.setUsername("UserA");
        Assertions.assertNull(userService.loginUser(body), "The password should be incorrect.");
        body.setPassword("PasswordA123");
        Assertions.assertNotNull(userService.loginUser(body), "The user should login successfully.");
    }

    
}
