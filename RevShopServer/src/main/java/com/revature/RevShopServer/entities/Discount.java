package com.revature.RevShopServer.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity(name = "product_discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "discount_id")
    private int discountId;

    @Positive(message = "Your product price must be positive")
    @Column(name = "discount_price")
    private float discountPrice;
    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    //Constructors
    public Discount(float discountPrice){
        this.discountPrice = discountPrice;
    }
    public Discount(){

    }


    //Getters and setters
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
