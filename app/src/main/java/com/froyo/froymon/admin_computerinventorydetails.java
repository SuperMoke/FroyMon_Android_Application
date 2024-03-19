package com.froyo.froymon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class admin_computerinventorydetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference myref;
    private ComputerAdapter computerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_computerinventorydetails);
        String labid = getIntent().getStringExtra("labid");

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myref = FirebaseDatabase.getInstance().getReference().child("student").child(labid);
        Query query = myref.orderByChild("computernumber");

        FirebaseRecyclerOptions<Computer> options =
                new FirebaseRecyclerOptions.Builder<Computer>()
                        .setQuery(query, Computer.class)
                        .build();

        computerAdapter = new ComputerAdapter(options);
        recyclerView.setAdapter(computerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        computerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        computerAdapter.stopListening();
    }
}