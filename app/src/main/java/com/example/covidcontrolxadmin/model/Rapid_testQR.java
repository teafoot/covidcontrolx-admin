package com.example.covidcontrolxadmin.model;

public class Rapid_testQR {
    private int swab_qty;

    public Rapid_testQR(int swab_qty) {
        this.swab_qty = swab_qty;
    }

    public Rapid_testQR() {
    }

    public void setSwab_qty(int swab_qty) {
        this.swab_qty = swab_qty;
    }

    public int getSwab_qty() {
        return this.swab_qty;
    }
}
