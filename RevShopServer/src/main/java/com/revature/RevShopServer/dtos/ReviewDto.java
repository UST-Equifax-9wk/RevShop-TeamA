package com.revature.RevShopServer.dtos;

public class ReviewDto {

    private Integer rating;
    private String description;
    private Integer productId;
    private Integer buyerId;

    public ReviewDto() {
    }

    public ReviewDto(Integer rating, String description, Integer productId, Integer buyerId) {
        this.rating = rating;
        this.description = description;
        this.productId = productId;
        this.buyerId = buyerId;
    }

    public ReviewDto(Integer rating, String description, Integer productId) {
        this.rating = rating;
        this.description = description;
        this.productId = productId;
    }

    public Integer getRating() {
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "rating=" + rating +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", buyerId=" + buyerId +
                '}';
    }
}
