package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
public class Admins {
    @Id
    @Column(name ="admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", nullable = false)
    private Accounts accounts;

    public Admins() {
    }

    public Admins(Accounts accounts) {
        this.accounts = accounts;
    }

    public Admins(Integer adminid) {
        this.adminid = adminid;
    }

    public Admins(Integer adminid, Accounts accounts) {
        this.adminid = adminid;
        this.accounts = accounts;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
}
