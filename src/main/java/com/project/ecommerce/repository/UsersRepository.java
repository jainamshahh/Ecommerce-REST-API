package com.project.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.ecommerce.model.Users;

public interface UsersRepository extends CrudRepository<Users,Long>{

    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

}
