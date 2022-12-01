package com.example.firebasefeedtest;

public class metaModel {

    String username,bestSquat, bestBenchpress, bestDeadlift, age, city, height;
    boolean publishDataToFeed;

    public metaModel(String username, String bestSquat, String bestBenchpress, String bestDeadlift, String age, String city, String height) {
        this.username = username;
        this.bestSquat = bestSquat;
        this.bestBenchpress = bestBenchpress;
        this.bestDeadlift = bestDeadlift;
        this.age = age;
        this.city = city;
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBestSquat() {
        return bestSquat;
    }

    public void setBestSquat(String bestSquat) {
        this.bestSquat = bestSquat;
    }

    public String getBestBenchpress() {
        return bestBenchpress;
    }

    public void setBestBenchpress(String bestBenchpress) {
        this.bestBenchpress = bestBenchpress;
    }

    public String getBestDeadlift() {
        return bestDeadlift;
    }

    public void setBestDeadlift(String bestDeadlift) {
        this.bestDeadlift = bestDeadlift;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
