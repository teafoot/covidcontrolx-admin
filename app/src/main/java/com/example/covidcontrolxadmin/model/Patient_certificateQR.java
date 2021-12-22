package com.example.covidcontrolxadmin.model;

public class Patient_certificateQR {
    private Object national_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String email;
    private String phone_number;
    private String vaccine_type;
    private Object vaccine_shot;
    private String vaccinationLocation;
    private String vaccinationDate;

    public Patient_certificateQR() {

    }

    public Patient_certificateQR(Object national_id, String first_name, String last_name, String date_of_birth, String email, String phone_number, String vaccine_type, Object vaccine_shot, String vaccinationLocation, String vaccinationDate) {
        this.national_id = national_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.phone_number = phone_number;
        this.vaccine_type = vaccine_type;
        this.vaccine_shot = vaccine_shot;
        this.vaccinationLocation = vaccinationLocation;
        this.vaccinationDate = vaccinationDate;
    }

    public Object getNational_id() {
        return national_id;
    }

    public void setNational_id(Object national_id) {
        this.national_id = national_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getVaccine_type() {
        return vaccine_type;
    }

    public void setVaccine_type(String vaccine_type) {
        this.vaccine_type = vaccine_type;
    }

    public Object getVaccine_shot() {
        return vaccine_shot;
    }

    public void setVaccine_shot(Object vaccine_shot) {
        this.vaccine_shot = vaccine_shot;
    }

    public String getVaccinationLocation() {
        return vaccinationLocation;
    }

    public void setVaccinationLocation(String vaccinationLocation) {
        this.vaccinationLocation = vaccinationLocation;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
