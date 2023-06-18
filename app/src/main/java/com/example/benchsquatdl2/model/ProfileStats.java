package com.example.benchsquatdl2.model;

public class ProfileStats {
    private double kilo;
    private String date;

    public ProfileStats(double kilo, String date) {
        this.kilo = kilo;
        this.date = date;
    }

    public double getKilo() {
        return kilo;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
