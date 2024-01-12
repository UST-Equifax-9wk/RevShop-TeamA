package com.revature.revshopserver.dtos;

import com.revature.revshopserver.entities.Seller;

public class SellerDto {
    private Integer seller;
    private String businessAddress;

    public SellerDto() {
    }
    public SellerDto(Seller seller) {
        this.seller = seller.getSellerId();
        this.businessAddress = seller.getBusinessAddress();
    }
    public SellerDto(Integer seller, String businessAddress) {
        this.seller = seller;
        this.businessAddress = businessAddress;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public String getBusiness_address() {
        return businessAddress;
    }

    public void setBusiness_address(String businessAddress) {
        this.businessAddress = businessAddress;
    }
}
