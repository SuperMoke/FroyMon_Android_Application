package com.froyo.froymon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class admin_createlobbyform extends AppCompatActivity {

    private EditText name,teacherid,email,subcode,classcode,classpress;
    private Button save;
    private ImageButton backbutton;

    private DatabaseReference myref;

    private Spinner lablist;

    private String labid;

    private  Map<String, String> labIdMap = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_createlobbyform);

        name = findViewById(R.id.nametf);
        teacherid = findViewById(R.id.teacheridtf);
        email = findViewById(R.id.emailtf);
        subcode = findViewById(R.id.subjecttf);
        classcode =  findViewById(R.id.classcodetf);
        classpress = findViewById(R.id.classpresstf);
        lablist = findViewById(R.id.spinner);
        save = findViewById(R.id.btnsave);
        backbutton = findViewById(R.id.btnbackbutton);
        myref = FirebaseDatabase.getInstance().getReference();

        labIdMap = new HashMap<>();
        labIdMap.put("Computer Laboratory 1", "CLAB1");
        labIdMap.put("Computer Laboratory 2", "CLAB2");
        labIdMap.put("Computer Laboratory 3", "CLAB3");
        labIdMap.put("Computer Laboratory 4", "CLAB4");
        labIdMap.put("Computer Laboratory 5", "CLAB5");
        labIdMap.put("Computer Laboratory 6", "CLAB6");
        labIdMap.put("Cisco Laboratory", "CiscoLab");
        labIdMap.put("Accounting Laboratory" , "AccountingLab");
        labIdMap.put("Hardware Laboratory" , "HardwareLab");
        labIdMap.put("Contact Center Laboratory" , "ContactCenterLab");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.lablist,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lablist.setAdapter(adapter);

        lablist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String labselected =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_createlobbyform.this,admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = name.getText().toString();
                String teacheridvalue = teacherid.getText().toString();
                String emailValue = email.getText().toString();
                String subcodeValue = subcode.getText().toString();
                String classcodeValue = classcode.getText().toString();
                String classpressValue = classpress.getText().toString();
                String labnameValue = lablist.getSelectedItem().toString();
                String labidValue = labIDsetter(labnameValue);
                String randomcode = generateRandomCode();

                if (nameValue.isEmpty() || teacheridvalue.isEmpty() || emailValue.isEmpty() || subcodeValue.isEmpty()
                        || classcodeValue.isEmpty() || classpressValue.isEmpty() || labnameValue.isEmpty()) {
                    Toast.makeText(admin_createlobbyform.this, "You must enter the complete details", Toast.LENGTH_SHORT).show();
                } else {
                    LobbyData lobbyData = new LobbyData(nameValue, teacheridvalue, emailValue, subcodeValue, classcodeValue, classpressValue, labnameValue, labidValue, randomcode);
                    myref.child("lobby").child(teacheridvalue).setValue(lobbyData);
                    DatabaseReference studentRef = myref.child("student").child(labidValue);
                    studentRef.child(teacheridvalue).setValue(lobbyData);
                    Intent intent = new Intent(admin_createlobbyform.this, admin_viewlobbyparticipants.class);
                    intent.putExtra("randomCode", randomcode);
                    intent.putExtra("labid", labidValue);
                    intent.putExtra("subject", subcodeValue);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private String labIDsetter(String labnameValue) {
        String labid = labIdMap.get(labnameValue);
        return labid;
    }


    private String generateRandomCode() {
        Random rand = new Random();
        int randomInt = rand.nextInt(10000);
        return String.format(Locale.US, "%06d", randomInt);
    }
}