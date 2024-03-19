package com.froyo.froymon;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class admin_viewlobbyparticipants extends AppCompatActivity {

    private TextView code;
    private Button endsession,savesession;
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    private userAdapter UserAdapter;
    private String subject;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_admin_viewlobbyparticipants);

        code = findViewById(R.id.txtcode);
        endsession = findViewById(R.id.btnend);
        savesession = findViewById(R.id.btnsave);
        recyclerView = findViewById(R.id.recyclerview);

        Intent intent = getIntent();
        String randomcode = intent.getExtras().getString("randomCode");
        String labid = intent.getExtras().getString("labid");
        subject = intent.getExtras().getString("subject");
        myref = FirebaseDatabase.getInstance().getReference().child("student").child(labid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(admin_viewlobbyparticipants.this));
        FirebaseRecyclerOptions<user> option = new FirebaseRecyclerOptions.Builder<user>().setQuery(myref,user.class).build();
        UserAdapter = new userAdapter(option);
        recyclerView.setAdapter(UserAdapter);
        code.setText("The lobby code is: " + randomcode);

        endsession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_viewlobbyparticipants.this,admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        savesession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToTxtFile();
            }
        });
    }

    private void saveDataToTxtFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
                return;
            }
            savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/Attendance/";
        }

        List<user> userList = UserAdapter.getCurrentList();
        StringBuilder dataStringBuilder = new StringBuilder();
        for (user user : userList) {
            String userData = user.getStudentname();
            dataStringBuilder.append(userData);
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_ ", Locale.getDefault()).format(new Date());
        String fileName = "userData_" + timeStamp + subject + ".txt";

        try {
            File directory = new File(savePath);
            if (!directory.exists()) {
                directory.mkdirs(); //
            }
            File file = new File(directory, fileName);
            FileWriter writer = new FileWriter(file);
            writer.append(dataStringBuilder.toString());
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save file", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveDataToTxtFile();
            } else {
                Toast.makeText(this, "Permission denied. File cannot be saved.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onStart() {
        super.onStart();
        UserAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        UserAdapter.stopListening();
    }
}