package com.example.covidcontrolxadmin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.covidcontrolxadmin.R;
import com.example.covidcontrolxadmin.adapter.CustomAdapterQR;
import com.example.covidcontrolxadmin.model.BookingQR;
import com.example.covidcontrolxadmin.model.HospitalQR;
import com.example.covidcontrolxadmin.model.Patient_certificateQR;
import com.example.covidcontrolxadmin.model.TimeslotQR;
import com.example.covidcontrolxadmin.viewmodel.QRViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusUpdateFragment extends Fragment implements CustomAdapterQR.UpdateStatusListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Patient_certificateQR> patients = new ArrayList<>();
    CustomAdapterQR adapterQR ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_status_update, container, false);

        getAllPatientsData(view);

        return view;
    }

    public void getAllPatientsData(View view){

        db.collection("hospitals").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                    HospitalQR h = doc.toObject(HospitalQR.class);

                    for(BookingQR b : h.getBookings()){
                        for(TimeslotQR t : b.getTimeslots()){
                            if(t.getPatient_certificate() != null){
                                Patient_certificateQR p = t.getPatient_certificate();

                                if(Long.parseLong(p.getVaccine_shot().toString()) < 3){
                                    patients.add(p);
//                                    Log.e("here", p.getFirst_name() + p.getNational_id() + " " +
//                                            p.getEmail() + " " + p.getVaccine_shot()
//                                            + " " + p.getVaccinationDate() + " " + count[0]++);

                                            //Lizeth.DAmore35@yahoo.com 2021-11-24 9179189

//                                    Log.e("test", p.get("national_id") + "\n" + p.get("first_name") + "\n "
//                                            + p.get("first_name") + " \n" + p.get("last_name") + " \n" + p.get("date_of_birth") + " \n" +
//                                            p.get("email") + " \n" + p.get("phone_number") + " \n" + p.get("vaccine_type") + " \n" +
//                                            p.get("vaccine_shot") + " \n" + p.get("vaccinationLocation") + " \n" + p.get("vaccinationDate"));

                                }
                            }
                        }
                    }
                }

                RecyclerView recyclerView = view.findViewById(R.id.containerQR);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

                DividerItemDecoration dividerItemDecoration =
                        new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);

                QRViewModel qrViewModel = new ViewModelProvider(requireActivity()).get(QRViewModel.class);
                qrViewModel.setPatientsList(patients);

                qrViewModel.getPatientsList().observe(requireActivity(), new Observer<List<Patient_certificateQR>>() {
                    @Override
                    public void onChanged(List<Patient_certificateQR> patient_certificateQRS) {
                        adapterQR = new CustomAdapterQR(patients, StatusUpdateFragment.this);
                        recyclerView.setAdapter(adapterQR);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("--", "Failure");
            }
        });
    }

    @Override
    public void updateStatus(String nationalId, String vaccinationDate) {
        db.collection("hospitals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        String hospitalId = doc.getId();
                        List<HashMap<String, Object>> bookingsMap = (List<HashMap<String, Object>>) doc.get("bookings");

                        for(HashMap<String, Object> b : bookingsMap){

                            List<HashMap<String, Object>> timeslotsMap = (List<HashMap<String, Object>>) b.get("timeslots");

                            for(HashMap<String, Object> t : timeslotsMap){

                                if(t.get("patient_certificate") != null){
                                    HashMap<String, Object> p = (HashMap<String, Object>) t.get("patient_certificate");

                                    if(p.get("national_id").toString().equals(nationalId) &&
                                            p.get("vaccinationDate").toString().equals(vaccinationDate)){
//                                    if(p.get("national_id").toString().equals("9179189") &&
//                                            p.get("vaccinationDate").toString().equals("2021-11-24")){
//                                        Log.e("test", p.get("national_id") + "\n" + p.get("first_name") + "\n "
//                                        + p.get("first_name") + " \n" + p.get("last_name") + " \n" + p.get("date_of_birth") + " \n" +
//                                                p.get("email") + " \n" + p.get("phone_number") + " \n" + p.get("vaccine_type") + " \n" +
//                                                p.get("vaccine_shot") + " \n" + p.get("vaccinationLocation") + " \n" + p.get("vaccinationDate"));

                                        Map<String, Object> outputP = new HashMap<>();
                                        outputP.put("national_id", p.get("national_id"));
                                        outputP.put("first_name", p.get("first_name"));
                                        outputP.put("last_name", p.get("last_name"));
                                        outputP.put("date_of_birth", p.get("date_of_birth"));
                                        outputP.put("email", p.get("email"));
                                        outputP.put("phone_number", p.get("phone_number"));
                                        outputP.put("vaccine_type", p.get("vaccine_type"));

                                        if(p.get("vaccine_shot").toString().equals("1")){
                                            outputP.put("vaccine_shot", 3);
                                        }
                                        else if (p.get("vaccine_shot").toString().equals("2")){
                                            outputP.put("vaccine_shot", 4);
                                        }

                                        outputP.put("vaccinationLocation", p.get("vaccinationLocation"));
                                        outputP.put("vaccinationDate", p.get("vaccinationDate"));

                                        t.put("patient_certificate", outputP);

                                    }
                                }
                            }
                        }

                        db.collection("hospitals").document(hospitalId).update("bookings", bookingsMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(), "Success on updating", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Failed to update", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}