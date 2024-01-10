package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.OrderItem;
import com.revature.revshopserver.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }
    public OrderItem save(OrderItem orderItem)
    {
        return orderItemRepository.save(orderItem);
    }
}
