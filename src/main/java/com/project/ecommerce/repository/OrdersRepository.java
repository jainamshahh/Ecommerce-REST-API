package com.project.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.project.ecommerce.model.Orders;
import com.project.ecommerce.model.Users;

public interface OrdersRepository extends ListCrudRepository<Orders,Long> {

    List<Orders> findByUser(Users user);

}
