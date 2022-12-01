package com.example.firebasefeedtest;

public class modelSquat {
    String squat1, squat2, squat3, kg1, kg2,kg3;

    public modelSquat(String squat1, String squat2, String squat3, String kg1, String kg2, String kg3) {
        this.squat1 = squat1;
        this.squat2 = squat2;
        this.squat3 = squat3;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
    }

    public String getSquat1() {
        return squat1;
    }

    public void setSquat1(String squat1) {
        this.squat1 = squat1;
    }

    public String getSquat2() {
        return squat2;
    }

    public void setSquat2(String squat2) {
        this.squat2 = squat2;
    }

    public String getSquat3() {
        return squat3;
    }

    public void setSquat3(String squat3) {
        this.squat3 = squat3;
    }

    public String getKg1() {
        return kg1;
    }

    public void setKg1(String kg1) {
        this.kg1 = kg1;
    }

    public String getKg2() {
        return kg2;
    }

    public void setKg2(String kg2) {
        this.kg2 = kg2;
    }

    public String getKg3() {
        return kg3;
    }

    public void setKg3(String kg3) {
        this.kg3 = kg3;
    }
}
