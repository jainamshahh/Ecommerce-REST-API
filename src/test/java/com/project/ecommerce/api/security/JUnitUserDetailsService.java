package com.project.ecommerce.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;

@Service
@Primary
public class JUnitUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    public JUnitUserDetailsService(@Autowired UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<Users> opUser = usersRepository.findByUsername(username);
        if (opUser.isPresent())
            return opUser.get();
        return null;
    }

}
