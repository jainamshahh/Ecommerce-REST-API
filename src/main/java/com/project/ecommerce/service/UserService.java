package com.project.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.api.model.UserRegisterationBody;
import com.project.ecommerce.exception.UserAlreadyExists;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;

@Service
public class UserService {

    private UsersRepository usersRepository;

    public UserService(@Autowired UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users regiserUser(UserRegisterationBody newUser) throws UserAlreadyExists{

        if(usersRepository.findByEmail(newUser.getEmail()).isPresent() || usersRepository.findByUsername(newUser.getUsername()).isPresent()){
            throw new UserAlreadyExists();
        }

        Users user = new Users();
        user.setUsername(newUser.getEmail());
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        return usersRepository.save(user);

    }

    

}
