package com.example.benchsquatdl2;

import java.util.ArrayList;

public class modelBenchList {
    String squat1,squat2, squat3, squat4, squat5, kg1, kg2, kg3, kg4, kg5,  time, xNote;
    private modelBenchList(){
    }

    public modelBenchList(String squat1, String squat2, String squat3, String squat4, String squat5, String kg1, String kg2, String kg3, String kg4, String kg5, String time, String xNote) {
        this.squat1 = squat1;
        this.squat2 = squat2;
        this.squat3 = squat3;
        this.squat4 = squat4;
        this.squat5 = squat5;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.kg4 = kg4;
        this.kg5 = kg5;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBenchList(String squat1, String squat2, String squat3, String squat4, String kg1, String kg2, String kg3, String kg4, String time, String xNote) {
        this.squat1 = squat1;
        this.squat2 = squat2;
        this.squat3 = squat3;
        this.squat4 = squat4;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.kg4 = kg4;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBenchList(String squat1, String squat2, String squat3, String kg1, String kg2, String kg3, String time, String xNote) {
        this.squat1 = squat1;
        this.squat2 = squat2;
        this.squat3 = squat3;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBenchList(String squat1, String squat2, String kg1, String kg2, String time, String xNote) {
        this.squat1 = squat1;
        this.squat2 = squat2;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBenchList(String squat1, String kg1, String time, String xNote) {
        this.squat1 = squat1;
        this.kg1 = kg1;
        this.time = time;
        this.xNote = xNote;
    }

    public String getSquat1() {
        return squat1;
    }

    public String getSquat2() {
        return squat2;
    }

    public String getSquat3() {
        return squat3;
    }

    public String getSquat4() {
        return squat4;
    }

    public String getSquat5() {
        return squat5;
    }

    public String getKg1() {
        return kg1;
    }

    public String getKg2() {
        return kg2;
    }

    public String getKg3() {
        return kg3;
    }

    public String getKg4() {
        return kg4;
    }

    public String getKg5() {
        return kg5;
    }

    public String getTime() {
        return time;
    }

    public String getxNote() {
        return xNote;
    }
}
