package com.revature.RevShopServer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Accounts {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountid;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @NotNull
    @Email
    @Size(min = 3, max = 50, message = "Email should be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Size(min = 9, max = 15, message = "Phone should be between 9 and 15 characters")
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String accountType;

    public Accounts(Integer accountid, String username, String password, String email, String phone, String accountType) {
        this.accountid = accountid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.accountType = accountType;
    }

    public Accounts(String username, String password, String email, String phone, String accountType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.accountType = accountType;
    }

    public Accounts(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Accounts() {

    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts account = (Accounts) o;
        return Objects.equals(accountid, account.accountid) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(email, account.email) && Objects.equals(phone, account.phone) && Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountid, username, password, email, phone, accountType);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
