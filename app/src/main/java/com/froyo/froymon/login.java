package com.froyo.froymon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    private Button login;
    private EditText email,pass;
    private TextView signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailtf);
        pass = findViewById(R.id.passwordtf);
        login = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.txtsignup);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ccaemail = email.getText().toString();
                String password = pass.getText().toString();
                if(ccaemail.equals("admin@cca.edu.ph") && password.equals("admin")){
                    Intent intent = new Intent(login.this, admin_homepage.class);
                    startActivity(intent);
                    finish();
                }
                if (!ccaemail.endsWith("cca.edu.ph")) {
                    Toast.makeText(login.this, "Invalid email domain", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(ccaemail,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(login.this,user_homepage.class);
                                startActivity(i);
                                finish();
                            } else {
                            }
                        }
                    });
                }
            }
        });
    }




}