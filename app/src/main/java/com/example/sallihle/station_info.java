package com.example.sallihle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class station_info extends AppCompatActivity {

    private static final String TAG = "station_info";

    private static final String provider_name = "user";
    private static final String provider_email = "email";
    private static final String provider_phone = "phone";

    private static final String commercial_number = "commercial number";
    private static final String service_type = "service type";

    EditText number ;
    String CUMMNUMBER ;
    String SERVICE_TYPE;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);

        mAuth = FirebaseAuth.getInstance();

        number =  findViewById(R.id.commer_number);
        CUMMNUMBER = number.getText().toString();



        Spinner service = (Spinner) findViewById(R.id.service_type);
        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
               SERVICE_TYPE=selected;
                // send car type to firebase
                Toast.makeText(station_info.this, selected, Toast.LENGTH_SHORT).show();            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void providersub(View view) {

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
                    user.put(provider_name,cname);
                    user.put(provider_email,cemail);
                    user.put(provider_phone,cphone);
                    user.put(service_type,SERVICE_TYPE);
                    user.put(commercial_number,CUMMNUMBER);



                    db.collection("users").document("mohamad").set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(station_info.this, "saved successfully", Toast.LENGTH_LONG).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(station_info.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, e.toString());
                                }
                            });



                    Intent intent = new Intent(station_info.this,MainActivity.class);
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




    }

//    public void providerhome(View view) {
//        Intent myint = new Intent(this,provider_home.class);
//        startActivity(myint);
//    }
}