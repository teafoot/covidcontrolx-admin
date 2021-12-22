package com.example.covidcontrolxadmin.model;

import java.util.List;

public class ServicesQR {
    private List<VaccinationQR> vaccination;
    private Pcr_testQR pcr_test;
    private Rapid_testQR rapid_test;

    public ServicesQR() {
    }

    public ServicesQR(List<VaccinationQR> vaccination, Pcr_testQR pcr_test, Rapid_testQR rapid_test) {
        this.vaccination = vaccination;
        this.pcr_test = pcr_test;
        this.rapid_test = rapid_test;
    }

    public List<VaccinationQR> getVaccination() {
        return vaccination;
    }

    public void setVaccination(List<VaccinationQR> vaccination) {
        this.vaccination = vaccination;
    }

    public Pcr_testQR getPcr_test() {
        return pcr_test;
    }

    public void setPcr_test(Pcr_testQR pcr_test) {
        this.pcr_test = pcr_test;
    }

    public Rapid_testQR getRapid_test() {
        return rapid_test;
    }

    public void setRapid_test(Rapid_testQR rapid_test) {
        this.rapid_test = rapid_test;
    }
}
