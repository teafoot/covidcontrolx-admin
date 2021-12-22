package com.example.covidcontrolxadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidcontrolxadmin.fragment.QRScanFragment;
import com.example.covidcontrolxadmin.fragment.StatusUpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginToStatusActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_status);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new StatusUpdateFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.updateStatus){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new StatusUpdateFragment()).commit();
        }
        if(item.getItemId() == R.id.codeScan){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new QRScanFragment()).commit();
        }

        return true;
    }
}