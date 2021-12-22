package com.example.covidcontrolxadmin.model;

import java.util.List;

public class VaccinationQR {
    private int vaccine_id;
    private String name;
    private String description;
    private String dosage_amount;
    private double efficacy;
    private List<String> side_effects;
    private int qty_available;

    public VaccinationQR(int vaccine_id, String name, String description, String dosage_amount, double efficacy, List<String> side_effects, int qty_available) {
        this.vaccine_id = vaccine_id;
        this.name = name;
        this.description = description;
        this.dosage_amount = dosage_amount;
        this.efficacy = efficacy;
        this.side_effects = side_effects;
        this.qty_available = qty_available;
    }

    public VaccinationQR() {
    }

    public void setVaccine_id(int vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    public int getVaccine_id() {
        return this.vaccine_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDosage_amount(String dosage_amount) {
        this.dosage_amount = dosage_amount;
    }

    public String getDosage_amount() {
        return this.dosage_amount;
    }

    public void setEfficacy(double efficacy) {
        this.efficacy = efficacy;
    }

    public double getEfficacy() {
        return this.efficacy;
    }

    public void setSide_effects(List<String> side_effects) {
        this.side_effects = side_effects;
    }

    public List<String> getSide_effects() {
        return this.side_effects;
    }

    public void setQty_available(int qty_available) {
        this.qty_available = qty_available;
    }

    public int getQty_available() {
        return this.qty_available;
    }
}
