package com.froyo.froymon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email,pass,confirmpass;
    private Button signup;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.emailtf);
        pass = findViewById(R.id.passwordtf);
        confirmpass = findViewById(R.id.confirmpasswordtf);
        signup = findViewById(R.id.btnsignup);
        login = findViewById(R.id.txtlogin);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ccaemail = email.getText().toString();
                String password = pass.getText().toString();
                String cpassword = confirmpass.getText().toString();

                if(!ccaemail.endsWith("cca.edu.ph")){
                    Toast.makeText(signup.this, "Wrong Email Domain", Toast.LENGTH_SHORT).show();
                } else if (password.equals(cpassword)){
                    mAuth.createUserWithEmailAndPassword(ccaemail,password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(signup.this, "The account has been created", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(signup.this, login.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(signup.this, "There is an error" + task, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else{
                    Toast.makeText(signup.this, "The password and confirm password do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}