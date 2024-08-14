package com.example.sec06.assignment;

public class Helper {
    public static Order contructOrder(String data) {
        String[] oderAsArrayString = data.split(":");

        return new Order(
                oderAsArrayString[0],
                oderAsArrayString[1],
                Integer.parseInt(oderAsArrayString[2]),
                Integer.parseInt(oderAsArrayString[3]));
    }
}
