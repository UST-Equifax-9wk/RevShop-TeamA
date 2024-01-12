package com.revature.revshopserver.dtos;

import com.revature.revshopserver.entities.Buyer;

public class BuyerDto {
    private Integer id;
    private String firstname;
    private String lastname;

    public BuyerDto() {
    }

    public BuyerDto(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public BuyerDto(Buyer buyer){
        this.id = buyer.getBuyerId();
        this.firstname = buyer.getFirstname();
        this.lastname = buyer.getLastname();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
