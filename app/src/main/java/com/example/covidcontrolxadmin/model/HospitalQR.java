package com.example.covidcontrolxadmin.model;

import java.util.List;

public class HospitalQR {
    private String hospital_id;
    private String name;
    private String phone;
    private String image;
    private LocationQR location;
    private ServicesQR services;
    private List<BookingQR> bookings;

    public HospitalQR() {
    }

    public HospitalQR(String hospital_id, String name, String phone, String image, LocationQR location, ServicesQR services, List<BookingQR> bookings) {
        this.hospital_id = hospital_id;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.location = location;
        this.services = services;
        this.bookings = bookings;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocationQR getLocation() {
        return location;
    }

    public void setLocation(LocationQR location) {
        this.location = location;
    }

    public ServicesQR getServices() {
        return services;
    }

    public void setServices(ServicesQR services) {
        this.services = services;
    }

    public List<BookingQR> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingQR> bookings) {
        this.bookings = bookings;
    }
}

