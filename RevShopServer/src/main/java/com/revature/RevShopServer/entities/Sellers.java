package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
public class Sellers {
    @Id
    @Column(name = "sellers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellersid;
    @OneToOne
    @JoinColumn(name = "sellers", nullable = false)
    private Accounts accounts;

    public Sellers(Integer sellersid, Accounts accounts) {
        this.sellersid = sellersid;
        this.accounts = accounts;
    }

    public Sellers(Integer sellersid) {
        this.sellersid = sellersid;
    }

    public Sellers() {
    }

    public Integer getSellersid() {
        return sellersid;
    }

    public void setSellersid(Integer sellersid) {
        this.sellersid = sellersid;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
}
