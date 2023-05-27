package com.example.benchsquatdl2.modelSpringer;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    //Nutzer
    private Long id;
    private String name;

    private List<Item> items = new ArrayList<>();

    public Cart(String name) {
        this.name = name;
    }

    public Cart() {

    }


    public void setItems(List<Item> items) {
        this.items = items;
    }

}
