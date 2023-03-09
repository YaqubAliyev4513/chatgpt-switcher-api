package com.chatgptswitcherapi.chatgptswitcherapi.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountKey;


    private boolean accountLimit = false;


    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(boolean accountLimit) {
        this.accountLimit = accountLimit;
    }



    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountKey='" + accountKey + '\'' +
                ", accountLimit='" + accountLimit + '\'' +
                '}';
    }
}
