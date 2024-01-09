package com.revature.RevShopServer.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @Column(name = "buyer_id")
    private Integer buyerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Review> reviews;

    public Buyer() {
    }

    public Buyer(Integer buyerId, Account account) {

        this.buyerId = buyerId;
        this.account = account;
    }

    public Buyer(Account account) {
        this.account = account;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(buyerId, buyer.buyerId) && Objects.equals(account, buyer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, account);
    }
}
