package com.project.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.model.Orders;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.OrdersRepository;

@Service
public class OrdersService {
    
    private OrdersRepository ordersRepository;

    public OrdersService(@Autowired OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

  
    public List<Orders> getOrders(Users user) {
        return ordersRepository.findByUser(user);
    }

}
