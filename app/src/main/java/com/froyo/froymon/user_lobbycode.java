package com.froyo.froymon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_lobbycode extends AppCompatActivity {

    private EditText lobbycode;
    private Button save;
    private ImageButton backbutton;

    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lobbycode);
        lobbycode = findViewById(R.id.codetf);
        save = findViewById(R.id.btnsave);
        backbutton = findViewById(R.id.btnbackbutton);
        myref = FirebaseDatabase.getInstance().getReference("lobby");
        Query query = myref.orderByChild("randomCode");
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String labid = intent.getExtras().getString("labid");

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_lobbycode.this, user_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot randomCodeshot = snapshot.child(id);
                        String code = randomCodeshot.child("randomCode").getValue(String.class);
                        String codelobby = lobbycode.getText().toString();
                        if(code.equals(codelobby)){
                            Intent i = new Intent(user_lobbycode.this,user_joinlobbyform.class);
                            i.putExtra("labid",labid);
                            startActivity(i);
                        } else {
                            Toast.makeText(user_lobbycode.this, "Invalid Lobby Code", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
}