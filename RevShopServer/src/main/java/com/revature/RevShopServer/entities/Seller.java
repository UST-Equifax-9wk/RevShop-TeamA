package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @Column(name = "seller_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;
    @OneToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Seller(Integer sellerId, Account account) {
        this.sellerId = sellerId;
        this.account = account;
    }

    public Seller(Account account) {
        this.account = account;
    }

    public Seller(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Seller() {
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Account getAccounts() {
        return account;
    }

    public void setAccounts(Account account) {
        this.account = account;
    }
}
