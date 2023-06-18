package com.example.benchsquatdl2.model;

import java.util.List;

public class modelStatisticData {

    Double Best3BenchKg;

    private modelStatisticData(){
    }

    public modelStatisticData(Double best3BenchKg) {
        Best3BenchKg = best3BenchKg;
    }

    public Double getBest3BenchKg() {
        return Best3BenchKg;
    }
}
