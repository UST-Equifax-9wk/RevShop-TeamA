package com.revature.revshopserver.dtos;

import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.entities.Seller;


public class ProductDto {
    private Integer productId;
    private String name;
    private String description;
    private String category;
    private float price;
    private int inventoryCount;
    private Integer seller;

    public ProductDto(Integer productId, String name, String description, String category, float price, int inventoryCount, Integer seller) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.inventoryCount = inventoryCount;
        this.seller = seller;
    }
    public ProductDto(Product product)
    {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.inventoryCount = product.getInventoryCount();
        this.seller = product.getSeller().getSellerId();
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }
}
