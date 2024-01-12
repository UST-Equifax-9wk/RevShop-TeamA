package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.entities.Order;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.BuyerRepository;
import com.revature.revshopserver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, BuyerRepository buyerRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
    }

    public Order save(Order order, Integer id) throws ObjectNotFoundException {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        if(buyer.isEmpty()){
            throw new ObjectNotFoundException("Cannot find buyer with that Id");
        }
        order.setBuyer(buyer.get());
        return orderRepository.save(order);
    }
}
