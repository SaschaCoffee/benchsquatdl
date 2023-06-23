package com.example.benchsquatdl2.model.modelSpringBoot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderRequest {

    @SerializedName("customer")
    @Expose
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "customer=" + customer +
                '}';
    }
}