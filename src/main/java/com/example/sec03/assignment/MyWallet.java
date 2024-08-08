package com.example.sec03.assignment;

public class MyWallet {

    private int quantity;
    private int balance;

    public MyWallet() {
        this.quantity = 0;
        this.balance = 1000;
    }

    public void buyOne(int price) {
        this.quantity++;
        this.balance = this.balance - price;
    }

    public void sellAll(int price) {
        this.balance = this.balance + this.quantity * price;
        this.quantity = 0;
    }

    public int getProfit() {
        return this.balance - 1000;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
