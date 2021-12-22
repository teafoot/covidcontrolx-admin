package com.example.covidcontrolxadmin.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.example.covidcontrolxadmin.R;

public class QRScanFragment extends Fragment {
    private CodeScanner codeScanner;
    private CodeScannerView codeScannerView;
    private TextView textView;
    private Button btnScan;

    public QRScanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_r_scan, container, false);

        codeScannerView = view.findViewById(R.id.scanner_view);
        textView = view.findViewById(R.id.textView);
        btnScan = view.findViewById(R.id.btnScan);

        codeScanner = new CodeScanner(getContext(), codeScannerView);

        codeScanner.setDecodeCallback(result -> getActivity().runOnUiThread(() -> {
            String vaccinationStatus = result.getText();

            textView.setText(vaccinationStatus);
        }));

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Vaccination Status");
                textView.setTextColor(getResources().getColor(R.color.black));
                checkPermission(Manifest.permission.CAMERA, 50);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        codeScanner.startPreview();
                    }
                }, 4000);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkPermission(Manifest.permission.CAMERA, 50);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                codeScanner.startPreview();
            }
        }, 4000);
    }

    @Override
    public void onStop() {
        super.onStop();
        codeScanner.stopPreview();
        codeScanner.releaseResources();
    }

    public void checkPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(requireActivity(), new String[]{permission}, requestCode);
        else{
            Toast.makeText(getContext(), "Permission provided", Toast.LENGTH_SHORT).show();
        }
    }
}