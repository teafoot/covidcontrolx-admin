package com.example.covidcontrolxadmin.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covidcontrolxadmin.model.Patient_certificateQR;

import java.util.List;


public class QRViewModel extends ViewModel {
    MutableLiveData<List<Patient_certificateQR>> patientsList = new MutableLiveData<>();

    public LiveData<List<Patient_certificateQR>> getPatientsList(){
        return patientsList;
    }

    public void setPatientsList(List<Patient_certificateQR> patientsList){
        this.patientsList.postValue(patientsList);
    }
}
