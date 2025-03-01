package com.project.ecommerce.api.controller.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Orders;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrdersService ordersService;

    public OrderController(@Autowired OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    
    @GetMapping
    public List<Orders> getOrders(@AuthenticationPrincipal Users user) {
        return ordersService.getOrders(user);
    }
}
