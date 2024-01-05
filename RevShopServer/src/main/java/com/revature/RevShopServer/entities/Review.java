package com.revature.RevShopServer.entities;


import jakarta.persistence.*;

@Entity(name = "product_reviews")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "rating")
    private float rating;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private String timeStamp;

    public Review(float rating){
        this.rating = rating;
    }

    public Review(){

    }

    //Getters and setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
