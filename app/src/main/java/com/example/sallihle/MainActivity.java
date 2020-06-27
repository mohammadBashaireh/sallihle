package com.example.sallihle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtemail, txtpassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        txtemail = findViewById(R.id.editTextemail);
        txtpassword = findViewById(R.id.editTextPassword);
    }

    public void register(View view) {
        Intent register = new Intent(this, regesration.class);
        startActivity(register);

    }

    public void login (View view)
    {
        String email = txtemail.getText().toString();
        String password = txtpassword.getText().toString();

        if(email.isEmpty()){
            txtemail.setError("email is required !!");
            txtemail.requestFocus();

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("email is not valid !!");
            txtemail.requestFocus();
        }else if(password.isEmpty()){
            txtpassword.setError("password is required !!");
            txtpassword.requestFocus();

        }else {

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"success!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,client_main_screen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option1:
                Intent myint = new Intent(this,client_car.class);
                startActivity(myint);


}
return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {


    }
}