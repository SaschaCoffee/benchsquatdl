package com.example.benchsquatdl2;

import java.util.HashMap;
import java.util.List;

public class modelDoubleList {

    List<Double> b;
    HashMap<String,Double> tx;

    public modelDoubleList(){
    }

    public modelDoubleList(List<Double> b) {
        this.b = b;
    }

    public modelDoubleList(HashMap<String, Double> tx) {
        this.tx = tx;
    }

    public List<Double> getB() {
        return b;
    }

    public HashMap<String, Double> getTx() {
        return tx;
    }
}
