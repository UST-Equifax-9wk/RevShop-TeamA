package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @Column(name ="admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Admin() {
    }

    public Admin(Account account) {
        this.account = account;
    }

    public Admin(Integer adminid) {
        this.adminid = adminid;
    }

    public Admin(Integer adminid, Account account) {
        this.adminid = adminid;
        this.account = account;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Account getAccounts() {
        return account;
    }

    public void setAccounts(Account account) {
        this.account = account;
    }
}
