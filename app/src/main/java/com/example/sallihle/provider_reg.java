package com.example.sallihle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class provider_reg extends AppCompatActivity {

    EditText txtusername, txtpassword, txtphone, txtemail,passwordconf;
    Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_reg);

        txtusername = findViewById(R.id.provider_name);
        txtpassword = findViewById(R.id.provider_pass);
        txtphone = findViewById(R.id.provider_phone);
        txtemail = findViewById(R.id.provider_email);
        passwordconf = findViewById(R.id.provider_passconf);

        info = findViewById(R.id.bbbbb);

    }

    public void stationinfo(View view) {

        String userName = txtusername.getText().toString();
        String password = txtpassword.getText().toString().trim();
        String Phone = txtphone.getText().toString().trim();
        String email = txtemail.getText().toString().trim();

        if (userName.isEmpty()){
            txtusername.setError("user name is required !!");
            txtusername.requestFocus();

        }else if(password.isEmpty() || password.length()<8 ){
            txtpassword.setError("password is required !!");
            txtpassword.requestFocus();

        }else if(Phone.isEmpty()){
            txtphone.setError("phone number is required !!");
            txtphone.requestFocus();

        }else if(email.isEmpty()){
            txtemail.setError("email is required !!");
            txtemail.requestFocus();

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("email is not valid !!");
            txtemail.requestFocus();
        }else{
            Intent i = new Intent(this,station_info.class);

            i.putExtra("USERNAME", userName);
            i.putExtra("PASSWORD", password);
            i.putExtra("EMAIL", email);
            i.putExtra("PHONE", Phone);

            startActivity(i);
        }



    }
}