package com.project.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Users;

public interface UsersRepository extends ListCrudRepository<Users,Long>{

    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

}
