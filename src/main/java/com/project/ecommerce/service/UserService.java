package com.project.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.api.model.LoginBody;
import com.project.ecommerce.api.model.UserRegisterationBody;
import com.project.ecommerce.exception.UserAlreadyExists;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;

@Service
public class UserService {

    private UsersRepository usersRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(@Autowired UsersRepository usersRepository,@Autowired EncryptionService encryptionService,@Autowired JWTService jwtService) {
        this.usersRepository = usersRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public Users registerUser(UserRegisterationBody newUser) throws UserAlreadyExists{

        if(usersRepository.findByEmail(newUser.getEmail()).isPresent() || usersRepository.findByUsername(newUser.getUsername()).isPresent()){
            throw new UserAlreadyExists();
        }

        Users user = new Users();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(encryptionService.encryptPassword(newUser.getPassword()));
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        return usersRepository.save(user);

    }

    public String loginUser(LoginBody loginBody) {
        
        Optional<Users> opUser = usersRepository.findByUsername(loginBody.getUsername());
        
        if (opUser.isPresent()) {
            
            Users user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

    

}
