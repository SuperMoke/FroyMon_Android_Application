package com.froyo.froymon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class admin_homepage extends AppCompatActivity {
    private ImageButton createlobby,computerinventory,generateqrcode;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        createlobby = findViewById(R.id.btncreatelobby);
        computerinventory = findViewById(R.id.btncomputerinventory);
        generateqrcode = findViewById(R.id.btnqrcodegenerator);
        logout = findViewById(R.id.btnlogout);

        createlobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_homepage.this, admin_createlobbyform.class);
                startActivity(intent);
            }
        });
        computerinventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_homepage.this, admin_computerinventory.class);
                startActivity(intent);
            }
        });

        generateqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_homepage.this, admin_qrcode_generator.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_homepage.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}