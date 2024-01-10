package com.revature.revshopserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @Column(name = "seller_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @OneToMany(mappedBy= "seller", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Product> products;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
