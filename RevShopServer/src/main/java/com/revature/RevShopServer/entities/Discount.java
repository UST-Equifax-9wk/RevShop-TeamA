package com.revature.RevShopServer.entities;


import jakarta.persistence.*;

@Entity(name = "product_discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "discount_id")
    private int discountId;

    @Column(name = "discount_price")
    private float discountPrice;
    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "product_id")
    private int productId;


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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }
}
