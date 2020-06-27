package com.example.sallihle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class regesration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regesration);
    }

    public void clientreg(View view) {
        Intent client_reg = new Intent(this,client__reg.class);
        startActivity(client_reg);
    }

    public void providerreg(View view) {
        Intent provider_reg = new Intent(this,provider_reg.class);
        startActivity(provider_reg);
    }
}