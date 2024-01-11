package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.OrderItem;
import com.revature.revshopserver.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {
    OrderItemService orderItemService;
    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @PostMapping("/additem")
    public OrderItem addItem(OrderItem orderItem)
    {
        return orderItemService.save(orderItem);
    }
}
