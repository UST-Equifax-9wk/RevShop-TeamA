package com.revature.RevShopServer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_id")
    private Integer reviewId;

    @PositiveOrZero(message = "You cannot rate something below 0!")
    @Max(5)
    @Column(name = "rating")
    private Integer rating;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp",
            columnDefinition = "timestamp USING \"timestamp\"::timestamp(6) with time zone",
            nullable = false)
    private ZonedDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonBackReference
    private Buyer author;

    public Review(Integer rating){
        this.rating = rating;
    }

    public Review(){

    }

    public Review(Integer rating, String description, ZonedDateTime timestamp, Product product) {
        this.rating = rating;
        this.description = description;
        this.timestamp = timestamp;
        this.product = product;
        this.author = null;
    }

    public Review(Integer rating, String description, ZonedDateTime timestamp, Product product, Buyer author) {
        this.rating = rating;
        this.description = description;
        this.timestamp = timestamp;
        this.product = product;
        this.author = author;
    }

    //Getters and setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getAuthor() {
        return author;
    }

    public void setAuthor(Buyer author) {
        this.author = author;
    }
}
