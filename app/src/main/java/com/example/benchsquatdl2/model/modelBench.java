package com.example.benchsquatdl2.model;

import java.util.ArrayList;

public class modelBench {
    String bench1, bench2, bench3, bench4,bench5, kg1, kg2, kg3, kg4, kg5, time, xNote;


    public modelBench() {
    }



    public modelBench(String bench1, String kg1, String time, String xNote) {
        this.bench1 = bench1;
        this.kg1 = kg1;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBench(String bench1, String bench2, String kg1, String kg2, String time, String xNote) {
        this.bench1 = bench1;
        this.bench2 = bench2;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBench(String bench1, String bench2, String bench3, String kg1, String kg2, String kg3, String time, String xNote) {
        this.bench1 = bench1;
        this.bench2 = bench2;
        this.bench3 = bench3;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBench(String bench1, String bench2, String bench3, String bench4, String kg1, String kg2, String kg3, String kg4, String time, String xNote) {
        this.bench1 = bench1;
        this.bench2 = bench2;
        this.bench3 = bench3;
        this.bench4 = bench4;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.kg4 = kg4;
        this.time = time;
        this.xNote = xNote;
    }

    public modelBench(String bench1, String bench2, String bench3, String bench4, String bench5, String kg1, String kg2, String kg3, String kg4, String kg5, String time, String xNote) {
        this.bench1 = bench1;
        this.bench2 = bench2;
        this.bench3 = bench3;
        this.bench4 = bench4;
        this.bench5 = bench5;
        this.kg1 = kg1;
        this.kg2 = kg2;
        this.kg3 = kg3;
        this.kg4 = kg4;
        this.kg5 = kg5;
        this.time = time;
        this.xNote = xNote;
    }

    public String getBench1() {
        return bench1;
    }

    public void setBench1(String bench1) {
        this.bench1 = bench1;
    }

    public String getBench2() {
        return bench2;
    }

    public void setBench2(String bench2) {
        this.bench2 = bench2;
    }

    public String getBench3() {
        return bench3;
    }

    public void setBench3(String bench3) {
        this.bench3 = bench3;
    }

    public String getBench4() {
        return bench4;
    }

    public void setBench4(String bench4) {
        this.bench4 = bench4;
    }

    public String getBench5() {
        return bench5;
    }

    public void setBench5(String bench5) {
        this.bench5 = bench5;
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

    public String getKg4() {
        return kg4;
    }

    public void setKg4(String kg4) {
        this.kg4 = kg4;
    }

    public String getKg5() {
        return kg5;
    }

    public void setKg5(String kg5) {
        this.kg5 = kg5;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getxNote() {
        return xNote;
    }

    public void setxNote(String xNote) {
        this.xNote = xNote;
    }
}
