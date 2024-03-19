package com.froyo.froymon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_qrcode_joinlobbyform extends AppCompatActivity {

    private EditText studentname,studentid,ccaemail;
    private Button save;
    private Spinner computerissue;
    private ImageButton backbutton;

    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_qrcode_joinlobbyform);

        studentname = findViewById(R.id.nametf);
        studentid = findViewById(R.id.studentidtf);
        ccaemail = findViewById(R.id.emailtf);
        computerissue = findViewById(R.id.spinnercomputerissue);
        save = findViewById(R.id.btnsave);
        backbutton = findViewById(R.id.btnbackbutton);
        Intent i = getIntent();
        String labid = i.getExtras().getString("lab");
        String computernumber = i.getExtras().getString("computerNumber");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.computerstatus,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        computerissue.setAdapter(adapter);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_qrcode_joinlobbyform.this, user_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentname.getText().toString().trim();
                String id = studentid.getText().toString().trim();
                String email = ccaemail.getText().toString().trim();
                String compstatus = computerissue.getSelectedItem().toString().trim();
                if (name.isEmpty() || id.isEmpty() || email.isEmpty() || compstatus.isEmpty()) {
                    Toast.makeText(user_qrcode_joinlobbyform.this, "You must enter the complete details", Toast.LENGTH_SHORT).show();
                } else {
                    myref = FirebaseDatabase.getInstance().getReference().child("student").child(labid).child(name);
                    user User = new user(name, id, email, computernumber, compstatus, labid);
                    myref.setValue(User);
                    Toast.makeText(user_qrcode_joinlobbyform.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    studentname.setText("");
                    studentid.setText("");
                    ccaemail.setText("");
                    Intent intent = new Intent(user_qrcode_joinlobbyform.this, user_thankyou.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}