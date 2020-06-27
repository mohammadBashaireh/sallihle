package com.example.sallihle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class client_request extends AppCompatActivity {
    private static final String TAG = "client_request";
    private static final String DESCRIPTION = "request descreption";
    private static final String REQUEST_TYPE = "request type";

    TextView t1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference reqRef = db.collection("requests").document("all requests");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_request);

        t1 =findViewById(R.id.textView_req);

    }

    public void getdata(){
         reqRef.get()
                 .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){

                        String description = documentSnapshot.getString(DESCRIPTION);
                        String req_type = documentSnapshot.getString(REQUEST_TYPE);

                        t1.setText("type :  "+req_type + "\ndescription  :"+description);

                    }else {


                        Toast.makeText(client_request.this, "document does not exist!!", Toast.LENGTH_SHORT).show();
                    }
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(client_request.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                         Log.d(TAG, e.toString());
                     }
                 });

    }



    @Override
    protected void onStart() {
        super.onStart();

        getdata();


    }
}