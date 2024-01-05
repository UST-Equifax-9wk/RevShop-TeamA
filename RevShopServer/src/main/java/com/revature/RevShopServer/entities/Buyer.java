package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @Column(name = "buyer_id")
    Buyer buyerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    Account account;

    public Buyer() {
    }


    public Buyer(Buyer buyerId, Accounts account) {
        this.buyerId = buyerId;
        this.account = account;
    }

    public Buyer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Buyer buyerId) {
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
