package com.example.covidcontrolxadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.covidcontrolxadmin.R;
import com.example.covidcontrolxadmin.model.Patient_certificateQR;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapterQR extends RecyclerView.Adapter<CustomAdapterQR.UpdateStatusViewHolder> {
    List<Patient_certificateQR> patientCertificateQRList = new ArrayList<>();
    UpdateStatusListener listener;

    public CustomAdapterQR(List<Patient_certificateQR> patientCertificateQRList, UpdateStatusListener listener) {
        this.patientCertificateQRList = patientCertificateQRList;
        this.listener = listener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @NonNull
    @Override
    public UpdateStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new UpdateStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateStatusViewHolder holder, int position) {
        holder.firstNameTextView.setText("First Name: " + patientCertificateQRList.get(position).getFirst_name());
        holder.nationalIdTextView.setText("National Id: " + patientCertificateQRList.get(position).getNational_id().toString());
        holder.vaccinationDateTextView.setText("Vaccination Date: " + patientCertificateQRList.get(position).getVaccinationDate());
        holder.dobTextView.setText("Date of Birth : " + patientCertificateQRList.get(position).getDate_of_birth().substring(0, 10));

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnUpdate.setText("Updated");
                holder.btnUpdate.setBackgroundColor(view.getResources().getColor(R.color.teal_200));
                holder.btnUpdate.setEnabled(false);
                listener.updateStatus(patientCertificateQRList.get(holder.getAdapterPosition()).getNational_id().toString(),
                        patientCertificateQRList.get(holder.getAdapterPosition()).getVaccinationDate());
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientCertificateQRList.size();
    }

    class UpdateStatusViewHolder extends RecyclerView.ViewHolder{
        TextView firstNameTextView;
        TextView vaccinationDateTextView;
        TextView nationalIdTextView;
        TextView dobTextView;
        Button btnUpdate;

        public UpdateStatusViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.firstNameTxtView);
            vaccinationDateTextView = itemView.findViewById(R.id.vaccinatationDateTxtView);
            nationalIdTextView = itemView.findViewById(R.id.nationalIdTxtView);
            dobTextView = itemView.findViewById(R.id.dobTextView);
            btnUpdate = itemView.findViewById(R.id.btnUpdateStatus);

        }

    }

    public interface UpdateStatusListener{
        void updateStatus(String nationalId, String vaccinationDate);
    }
}
