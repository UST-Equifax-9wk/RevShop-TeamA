package com.revature.revshopserver.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellers_id")
    private Seller sellers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Integer orderItemId, Seller sellers, Product product, Order order, Integer amount, Double price) {
        this.orderItemId = orderItemId;
        this.sellers = sellers;
        this.product = product;
        this.order = order;
        this.amount = amount;
        this.price = price;
    }

    public OrderItem(Seller sellers, Product product, Order order, Integer amount, Double price) {
        this.sellers = sellers;
        this.product = product;
        this.order = order;
        this.amount = amount;
        this.price = price;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Seller getSellers() {
        return sellers;
    }

    public void setSellers(Seller sellers) {
        this.sellers = sellers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", sellers=" + sellers +
                ", product=" + product +
                ", order=" + order +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
