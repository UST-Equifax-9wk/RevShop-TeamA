package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name ="admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public Admin() {
    }

    public Admin(Account account) {
        this.account = account;
    }

    public Admin(Integer adminId) {
        this.adminId = adminId;
    }

    public Admin(Integer adminId, Account account) {
        this.adminId = adminId;
        this.account = account;
    }

    public Integer getadminId() {
        return adminId;
    }

    public void setadminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Account getAccounts() {
        return account;
    }

    public void setAccounts(Account account) {
        this.account = account;
    }
}
