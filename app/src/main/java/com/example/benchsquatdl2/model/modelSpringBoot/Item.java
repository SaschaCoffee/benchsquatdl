package com.example.benchsquatdl2.model.modelSpringBoot;

public class Item {
    //TE
    private Long id;
    private String serialNumber;
    private Cart cart;

    public Item(String serialNumber, Cart cart) {
        this.serialNumber = serialNumber;
        this.cart = cart;
    }

    public Item(){

    }
}
