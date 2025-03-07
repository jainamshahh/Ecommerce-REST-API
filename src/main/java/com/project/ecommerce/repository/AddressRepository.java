package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Address;

public interface AddressRepository extends ListCrudRepository<Address,Long>{
    
    List<Address> findByUser_Id(Long id);
    
}
