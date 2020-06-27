package com.example.sallihle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.Listitem;

public class provider_home extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Listitem> listitems ;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);

        recyclerView = findViewById(R.id.client_requests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();

        Listitem item1 = new Listitem("Mohammad Kh","Electronic");
        Listitem item2 = new Listitem("Khaled Sa ", "Mechanic" );
        Listitem item3 = new Listitem("Ahmed Wx", "Out of gas" );
        Listitem item4 = new Listitem("yahia VS", "Other" );

        listitems.add(item1);
        listitems.add(item2);
        listitems.add(item3);
        listitems.add(item4);

        adapter = new MyAdapter(this, listitems) ;
        recyclerView.setAdapter(adapter);
    }
}