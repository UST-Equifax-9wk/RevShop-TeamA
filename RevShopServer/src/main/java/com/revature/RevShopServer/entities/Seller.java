package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
public class Seller {
    @Id
    @Column(name = "sellers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellersid;
    @OneToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Seller(Integer sellersid, Account account) {
        this.sellersid = sellersid;
        this.account = account;
    }

    public Seller(Integer sellersid) {
        this.sellersid = sellersid;
    }

    public Seller() {
    }

    public Integer getSellerid() {
        return sellersid;
    }

    public void setSellerid(Integer sellersid) {
        this.sellersid = sellersid;
    }

    public Account getAccounts() {
        return account;
    }

    public void setAccounts(Account account) {
        this.account = account;
    }
}
