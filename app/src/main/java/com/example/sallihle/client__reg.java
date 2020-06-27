package com.example.sallihle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class client__reg extends AppCompatActivity implements View.OnClickListener {


    EditText txtusername, txtpassword, txtphone, txtemail,passwordconf;
    Button cari;


    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__reg);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

       txtusername = findViewById(R.id.client_name);
       txtpassword = findViewById(R.id.client_pass);
       txtphone = findViewById(R.id.client_phone);
       txtemail = findViewById(R.id.client_email);
       passwordconf = findViewById(R.id.provider_passconf);

        cari = findViewById(R.id.car_info);


        cari.setOnClickListener(this);


    }




//    public void reg(View view) {
//
//        String userName = txtusername.getText().toString().trim();
//        String password = txtpassword.getText().toString().trim();
//        String Phone = txtphone.getText().toString().trim();
//        String email = txtemail.getText().toString().trim();
//
//
//
//        if (userName.isEmpty()){
//            txtusername.setError("user name is required !!");
//            txtusername.requestFocus();
//
//        }else if(password.isEmpty() || password.length()<8){
//            txtpassword.setError("password is required !!");
//            txtpassword.requestFocus();
//
//        }else if(Phone.isEmpty()){
//            txtphone.setError("phone number is required !!");
//            txtphone.requestFocus();
//
//        }else if(email.isEmpty()){
//            txtemail.setError("email is required !!");
//            txtemail.requestFocus();
//
//        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            txtemail.setError("email is not valid !!");
//            txtemail.requestFocus();
//        }
//        else {
//
//            Intent data =new Intent(this,client_car.class);
//
//            Intent intent = new Intent(getApplicationContext(), client_car.class);
//            intent.putExtra("USERNAME", userName);
//           intent.putExtra("PASSWORD", password);
//            intent.putExtra("EMAIL", email);
//            intent.putExtra("PHONE", Phone);
//            startActivity(intent);
//
//        }
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.car_info :
                String userName = txtusername.getText().toString().trim();
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
                    Intent i = new Intent(getApplicationContext(),client_car.class);

                    i.putExtra("USERNAME", userName);
                    i.putExtra("PASSWORD", password);
                    i.putExtra("EMAIL", email);
                    i.putExtra("PHONE", Phone);
                    startActivity(i);

                }

                break;

        }
    }


    //  public void registeruser () {

//        String userName = txtusername.getText().toString().trim();
//        String password = txtpassword.getText().toString().trim();
//        String Phone = txtphone.getText().toString().trim();
//        String email = txtemail.getText().toString().trim();
//
//
//
//        if (userName.isEmpty()){
//            txtusername.setError("user name is required !!");
//            txtusername.requestFocus();
//
//        }else if(password.isEmpty() || password.length()<8){
//            txtpassword.setError("password is required !!");
//            txtpassword.requestFocus();
//
//        }else if(Phone.isEmpty()){
//            txtphone.setError("phone number is required !!");
//            txtphone.requestFocus();
//
//        }else if(email.isEmpty()){
//            txtemail.setError("email is required !!");
//            txtemail.requestFocus();
//
//        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            txtemail.setError("email is not valid !!");
//            txtemail.requestFocus();
//        }
//        else {
//
//            Intent intent = new Intent(client__reg.this,client_car.class);
//            intent.putExtra("USERNAME",userName);
//            intent.putExtra("PASSWORD",password);
//            intent.putExtra("EMAIL",email);
//            intent.putExtra("PHONE",Phone);
//            startActivity(intent);
//
////            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////                @Override
////                public void onComplete(@NonNull Task<AuthResult> task) {
////                    if (task.isSuccessful()) {
////                      //  Toast.makeText(getApplicationContext(), "succefull register", Toast.LENGTH_SHORT).show();
////                        // Intent intent = new Intent(getApplicationContext(),client_car.class);
////
////                       // Intent myint = new Intent(this,MainActivity.class);
////                        //startActivity(myint);
////                       // finish();
////                    }else {
////
////                        if (task.getException() instanceof FirebaseAuthUserCollisionException ){
////                            Toast.makeText(getApplicationContext(), "email exists !!!", Toast.LENGTH_SHORT).show();
////                        }
////                        else {
////
////                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
////                        }
////
////                        // Toast.makeText(getApplicationContext(), "not succefull register", Toast.LENGTH_SHORT).show();
////
////                    }
////
////                }
////            });
//
//
//
//        }

 //   }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId())
//        {
//            case R.id.signup:
//
//                registeruser();
//                break;
//        }
//    }


}