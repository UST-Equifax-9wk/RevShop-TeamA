package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.Order;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/setorder")
    public Order newOrder(@RequestBody Order order, @RequestParam Integer id) throws ObjectNotFoundException {
        return orderService.save(order,id);
    }
}
