package com.example.covidcontrolxadmin.model;

public class Pcr_testQR {
    private int swab_qty;

    public Pcr_testQR() {
    }

    public Pcr_testQR(int swab_qty) {
        this.swab_qty = swab_qty;
    }

    public void setSwab_qty(int swab_qty) {
        this.swab_qty = swab_qty;
    }

    public int getSwab_qty() {
        return this.swab_qty;
    }
}
