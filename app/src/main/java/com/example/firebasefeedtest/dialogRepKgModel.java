package com.example.firebasefeedtest;

public class dialogRepKgModel {
    String firstkg,firstrep, secondkg, secondrep;

    public dialogRepKgModel(String firstkg, String firstrep, String secondkg, String secondrep) {
        this.firstkg = firstkg;
        this.firstrep = firstrep;
        this.secondkg = secondkg;
        this.secondrep = secondrep;
    }

    public String getFirstkg() {
        return firstkg;
    }

    public void setFirstkg(String firstkg) {
        this.firstkg = firstkg;
    }

    public String getFirstrep() {
        return firstrep;
    }

    public void setFirstrep(String firstrep) {
        this.firstrep = firstrep;
    }

    public String getSecondkg() {
        return secondkg;
    }

    public void setSecondkg(String secondkg) {
        this.secondkg = secondkg;
    }

    public String getSecondrep() {
        return secondrep;
    }

    public void setSecondrep(String secondrep) {
        this.secondrep = secondrep;
    }
}
