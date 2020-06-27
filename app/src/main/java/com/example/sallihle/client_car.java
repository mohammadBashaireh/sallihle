package com.example.sallihle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class client_car extends AppCompatActivity  {
    private static final String TAG = "client_car";
    private static final String client_name = "user";
    private static final String client_email = "email";
    private static final String client_phone = "phone";
    private static final String client_car_number = "client car number";
    private static final String car_type = "car type";
    private static final String car_gear_type = "car gear type";
    private static final String car_engine_type = "car engine type";
    private static String car_color = "car color";

  EditText number ;
  String CARTYPE ;
  String CLIENT_CAR_NUMBER;
  String CAR_GEAR_TYPE;
  String CAR_ENGINE_TYPE;
  String CAR_COLOR;


    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_car);

        mAuth = FirebaseAuth.getInstance();

       number =  findViewById(R.id.car_number);
        CLIENT_CAR_NUMBER = number.getText().toString();




        Spinner car_type = (Spinner) findViewById(R.id.car_type);
        car_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             String selectedcar = parent.getItemAtPosition(position).toString();
                // send car type to firebase
                CARTYPE = selectedcar;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner car_engine = (Spinner) findViewById(R.id.car_engine);
        car_engine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                    CAR_ENGINE_TYPE=selected;
                // send car type to firebase

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner car_gear = (Spinner) findViewById(R.id.gear_type);
        car_gear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                CAR_GEAR_TYPE = selected;
                // send car type to firebase


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner car_col = (Spinner) findViewById(R.id.car_color);
        car_col.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
               CAR_COLOR = selected;
                // send car type to firebase


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void client_sup(View view) {

        final String cemail = getIntent().getExtras().getString("EMAIL");
        final String cname = getIntent().getExtras().getString("USERNAME");
        String cpassword = getIntent().getExtras().getString("PASSWORD");
        final String cphone = getIntent().getExtras().getString("PHONE");


        mAuth.createUserWithEmailAndPassword( cemail , cpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                      Toast.makeText(getApplicationContext(), "succefull register", Toast.LENGTH_SHORT).show();


                    Map<String,Object> user = new HashMap<>();
                    user.put(client_name,cname);
                    user.put(client_email,cemail);
                    user.put(car_color,CAR_COLOR);
                    user.put(client_phone,cphone);
                    user.put(client_car_number,CLIENT_CAR_NUMBER);
                    user.put(car_type,CARTYPE);
                    user.put(car_gear_type,CAR_GEAR_TYPE);
                    user.put(car_engine_type,CAR_ENGINE_TYPE);

                    db.collection("users").document(cname).set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(client_car.this, "saved successfully", Toast.LENGTH_LONG).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(client_car.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, e.toString());
                                }
                            });



                     Intent intent = new Intent(client_car.this,MainActivity.class);
                    startActivity(intent);

                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "email exists !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


}}
