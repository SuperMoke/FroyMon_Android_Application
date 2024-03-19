package com.froyo.froymon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class user_homepage extends AppCompatActivity {

    private SearchView search;
    private DatabaseReference myref;
    private Button logout,scanqrcode;
    private RecyclerView recyclerView;

    private LaboratoryAdapter laboratoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        search = findViewById(R.id.svsearch);
        recyclerView = findViewById(R.id.recyclerview);
        logout = findViewById(R.id.btnlogout);
        scanqrcode = findViewById(R.id.btnscan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myref = FirebaseDatabase.getInstance().getReference("lobby");
        Query query = myref.orderByChild("labid");
        FirebaseRecyclerOptions<laboratory> options =
                new FirebaseRecyclerOptions.Builder<laboratory>()
                        .setQuery(query, laboratory.class)
                        .build();

        laboratoryAdapter = new LaboratoryAdapter(options, new LaboratoryAdapter.OnItemClickListener() {
            @Override
            public  void onItemClick(String id, String labid){
                Intent intent = new Intent(user_homepage.this,user_lobbycode.class);
                intent.putExtra("id",id);
                intent.putExtra("labid",labid);
                startActivity(intent);
                finish();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Query searchQuery = myref.orderByChild("labname").startAt(query).endAt(query + "\uf8ff");
                FirebaseRecyclerOptions<laboratory> searchoptions =
                        new FirebaseRecyclerOptions.Builder<laboratory>()
                                .setQuery(searchQuery, laboratory.class)
                                .build();
                laboratoryAdapter.updateOptions(searchoptions);
                recyclerView.setAdapter(laboratoryAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Query searchQuery = myref.orderByChild("labname").startAt(newText).endAt(newText + "\uf8ff");
                FirebaseRecyclerOptions<laboratory> searchoptions =
                        new FirebaseRecyclerOptions.Builder<laboratory>()
                                .setQuery(searchQuery, laboratory.class)
                                .build();
                laboratoryAdapter.updateOptions(searchoptions);
                recyclerView.setAdapter(laboratoryAdapter);
                return true;
            }
        });
        recyclerView.setAdapter(laboratoryAdapter);
        scanqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_homepage.this,user_scanqrcode.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_homepage.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        laboratoryAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        laboratoryAdapter.stopListening();
    }
}
