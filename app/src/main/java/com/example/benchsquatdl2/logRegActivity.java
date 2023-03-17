package com.example.benchsquatdl2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logRegActivity extends AppCompatActivity {
    private TextView register, forgetpw;
    private EditText et_ename, et_pw;
    private Button btn_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logregactivity);

        register = findViewById(R.id.tv_register);
        forgetpw = findViewById(R.id.tv_forget_pw);
        btn_login = findViewById(R.id.btn_login);
        et_ename = findViewById(R.id.et_ename);
        et_pw = findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register:
                startActivity(new Intent(this, registerUser.class));
                break;

        }
    }

    private void userLogin() {
        Toast.makeText(this, "21", Toast.LENGTH_SHORT).show();
        String email = et_ename.getText().toString().trim();
        String password = et_pw.getText().toString().trim();

        if(email.isEmpty()){
            et_ename.setError("email is required");
            et_ename.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_ename.setError("Please enter a valid email");
            et_ename.requestFocus();
            return;
        }
        if(password.isEmpty()){
            et_pw.setError("Password is required");
            et_pw.requestFocus();
            return;
        }

        if(password.length() < 6){
            et_pw.setError("Min pw length is 6 characters");
            et_pw.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity((new Intent(logRegActivity.this, listCountriesMainActivity.class)));
                    Toast.makeText(logRegActivity.this, "SUCCESS BRO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(logRegActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}