package com.example.sec03.assignment;

public class MyWallet {

    private int quantity;
    private int balance;

    public MyWallet(int quantity, int balance) {
        this.quantity = quantity;
        this.balance = balance;
    }

    public void buyOne(int price) {
        this.quantity++;
        this.balance = this.balance - price;
    }

    public void sellAll(int price) {
        this.balance = this.balance + this.quantity * price;
        this.quantity = 0;
    }

    public int getBalance() {
        return balance;
    }
}
