package com.example.sallihle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class request_details extends AppCompatActivity {
    private TextView name,description;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        name=findViewById(R.id.textView16);
        description=findViewById(R.id.textView17);
        extras=getIntent().getExtras();
        if(extras!=null)
        {
            name.setText(extras.getString("name"));
            description.setText(extras.getString("description"));
        }
    }
}