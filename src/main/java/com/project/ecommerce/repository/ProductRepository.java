package com.project.ecommerce.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Product;

public interface ProductRepository extends ListCrudRepository<Product,Long>{

}
