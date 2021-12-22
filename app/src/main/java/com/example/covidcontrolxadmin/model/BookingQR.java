package com.example.covidcontrolxadmin.model;

import java.util.List;

public class BookingQR {
    private String date;
    private List<TimeslotQR> timeslots;

    public BookingQR() {

    }

    public BookingQR(String date, List<TimeslotQR> timeslots) {
        this.date = date;
        this.timeslots = timeslots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TimeslotQR> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List<TimeslotQR> timeslots) {
        this.timeslots = timeslots;
    }
}
