package com.froyo.froymon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class admin_computerinventory extends AppCompatActivity {
    private ImageButton computerlab1,computerlab2,computerlab3,computerlab4,computerlab5,computerlab6,ciscolab,accountinglab,hardwarelab,contactcenterlab,backbutton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_computerinventory);

        computerlab1 =  findViewById(R.id.btncomputerlab1);
        computerlab2 =  findViewById(R.id.btncomputerlab2);
        computerlab3 =  findViewById(R.id.btncomputerlab3);
        computerlab4 =  findViewById(R.id.btncomputerlab4);
        computerlab5 =  findViewById(R.id.btncomputerlab5);
        computerlab6 =  findViewById(R.id.btncomputerlab6);
        ciscolab = findViewById(R.id.btnciscolab);
        accountinglab = findViewById(R.id.btnaccountinglab);
        hardwarelab = findViewById(R.id.btnhardwarelab);
        contactcenterlab = findViewById(R.id.btncontactcenterlab);
        backbutton = findViewById(R.id.btnbackbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_computerinventory.this,admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        computerlab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB1";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });

        computerlab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB2";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        computerlab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB3";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        computerlab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB4";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        computerlab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB5";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        computerlab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CLAB6";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });

        ciscolab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "CiscoLab";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        accountinglab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "AccountingLab";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        hardwarelab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "HardwareLab";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });
        contactcenterlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labid = "ContactCenterLab";
                Intent intent = new Intent(admin_computerinventory.this,admin_computerinventorydetails.class);
                intent.putExtra("labid",labid);
                startActivity(intent);
            }
        });

    }
}