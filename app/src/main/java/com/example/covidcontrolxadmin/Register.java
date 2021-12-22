package com.example.covidcontrolxadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Register extends AppCompatActivity {

    private EditText txtEmail, txtHospitalCode, txtPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.txtRegisterEmail);
        txtPassword = findViewById(R.id.txtRegisterPassword);
        txtHospitalCode = findViewById(R.id.txtHospitalCode);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    RegisterAdmin();
            }
        });
    }

    private void RegisterAdmin(){

        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String hospitalCode = txtHospitalCode.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            txtEmail.setError("Email Required");
            txtEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            txtPassword.setError("Password Required");
            txtPassword.requestFocus();
        }else if(TextUtils.isEmpty(hospitalCode)){
            txtHospitalCode.setError("Hospital Code Required");
            txtHospitalCode.requestFocus();
        }else {

            CollectionReference dbHospital = db.collection("hospitals");
            dbHospital.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                            String a = (String) documentSnapshot.get("code");
                            if (!a.equals(hospitalCode) && a.equals(hospitalCode)){
                                txtHospitalCode.setError("Incorrect Hospital Code");
                                txtHospitalCode.requestFocus();
                                txtHospitalCode.setText("");
                            }else if(a.equals(hospitalCode)){
                                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            CollectionReference dbUsers = db.collection("Users");

                                            //store additional fields in firestore database
                                            RegisterUser user = new RegisterUser(email, hospitalCode);

                                            dbUsers.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Register.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        } else {
                                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                txtHospitalCode.requestFocus();
                                txtHospitalCode.setText("");
                            }
                        }
                    }
                }
            });
        }
    }

}