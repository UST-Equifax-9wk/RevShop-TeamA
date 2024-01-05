package com.revature.RevShopServer.entities;

import com.revature.RevShopServer.enums.OrderStatus;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Order {
    private Long orderId;

    private OrderStatus orderStatus;

    private String shippingAddress;

    private String billingAddress;

    private LocalDateTime timestamp;

    private BigDecimal amount;

    public Order() {
    }

    public Order(Long orderId, OrderStatus orderStatus, String shippingAddress, String billingAddress, LocalDateTime timestamp, BigDecimal amount) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
