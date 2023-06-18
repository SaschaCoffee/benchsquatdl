package com.example.benchsquatdl2.model.modelSpringBoot;

import java.util.List;

public class Customer {
    private int id;

    private String name;

    private String email;

    private String gender;

    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public Customer(int id, String name, String email, String gender, List<Product> products) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", products=" + products +
                '}';
    }
}
