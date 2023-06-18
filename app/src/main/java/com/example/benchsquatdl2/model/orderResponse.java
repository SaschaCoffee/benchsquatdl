package com.example.benchsquatdl2.model;

public class orderResponse {
    private String name;
    private String productName;
    private int price;

    public orderResponse(String name, String productName, int price) {
        this.name = name;
        this.productName = productName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }
}
