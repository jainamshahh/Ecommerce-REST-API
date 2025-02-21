package com.project.ecommerce.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.api.model.LoginBody;
import com.project.ecommerce.api.model.LoginResponse;
import com.project.ecommerce.api.model.UserRegisterationBody;
import com.project.ecommerce.exception.UserAlreadyExists;
import com.project.ecommerce.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    public AuthController(@Autowired UserService userService){
        this.userService=userService;
    }

    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterationBody newUser){

        try{
            userService.regiserUser(newUser);
            return ResponseEntity.ok().build();
        }
        catch(UserAlreadyExists e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {

        
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } 
        else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

}
