package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @Column(name = "buyer_id")
    private Integer buyerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public Buyer() {
    }

    public Buyer(Integer buyerId, Accounts account) {
        this.buyerId = buyerId;
        this.account = account;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
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
