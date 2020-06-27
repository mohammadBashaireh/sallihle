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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class client_main_screen extends AppCompatActivity {

    private static final String TAG = "client_main_screen";

    private static final String DESCRIPTION = "request descreption";
    private static final String REQUEST_TYPE = "request type";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText desc ;
    String service_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main_screen);

        desc = findViewById(R.id.editTextTextMultiLine);



        Spinner service1 = (Spinner) findViewById(R.id.client_service);
        service1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected=parent.getItemAtPosition(position).toString();
                service_type=selected;
                Toast.makeText(client_main_screen.this, selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void request(View view) {
        String description = desc.getText().toString();

        Map<String,Object> requests = new HashMap<>();
        requests.put(DESCRIPTION,description);
        requests.put(REQUEST_TYPE,service_type);

        db.collection("requests").document("all requests").set(requests)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(client_main_screen.this, "saved successfully", Toast.LENGTH_LONG).show();

                        Intent request = new Intent(client_main_screen.this , client_request.class);
                        startActivity(request);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(client_main_screen.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());

                    }
                });



    }

    public void location(View view) {
        Intent location = new Intent(this , client_location.class);
        startActivity(location);
    }
}