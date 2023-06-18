package com.example.benchsquatdl2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.benchsquatdl2.Fragment.FragmentMainActivity;
import com.example.benchsquatdl2.model.metaModel;
import com.example.benchsquatdl2.model.modelLogRegister.userModel;
import com.example.benchsquatdl2.model.modelApi.userModelApi;
import com.example.benchsquatdl2.retrofit.UserApi;
import com.example.benchsquatdl2.retrofit.RetrofitService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText et_name, et_age, et_email, et_pw, et_city, et_bestsquat, et_bestbench, et_bestdeadlift, et_height,et_pw_repeat;

    private Button btn_register;
    private ProgressBar pg_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);

        mAuth = FirebaseAuth.getInstance();
        pg_bar = findViewById(R.id.progressBar);
        et_name = findViewById(R.id.et_register_name);
        et_email = findViewById(R.id.et_register_email);
        et_pw = findViewById(R.id.et_register_password);
        et_pw_repeat = findViewById(R.id.et_register_password_repeat);
      /*  et_city = findViewById(R.id.et_register_city);
        et_bestsquat = findViewById(R.id.et_max_squat_register);
        et_bestbench = findViewById(R.id.et_max_bench_register);
        et_bestdeadlift = findViewById(R.id.et_max_deadlift_register);*/


        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String pw = et_pw.getText().toString().trim();



        if(name.isEmpty()){
            et_name.setError("Full name is required");
            et_name.requestFocus();
            return;
        }

        if(email.isEmpty()){
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please provide valid email");
            et_email.requestFocus();
            return;
        }

        if(!pw.equals(et_pw_repeat.getText().toString().trim())){
            et_pw.setError("Please provide valid PW");
            et_pw.requestFocus();
            return;
        }

        if(pw.isEmpty()){
            et_pw.setError("Pw is required");
            et_pw.requestFocus();
            return;
        }

        if(pw.length() < 6) {
            et_pw.setError("Min pw length should be 6 characters");
            et_pw.requestFocus();
            return;
        }





        pg_bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(registerUser.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    userModel user = new userModel(name, "null","null" );
                    metaModel meta = new metaModel(name,null,null,null,null,null,null);
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        RetrofitService retrofitService = new RetrofitService();
                                        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

                                        String name = user.getDisplayName();

                                        String tokee = user.getUid();
                                        userModelApi xx = new userModelApi();

                                        xx.setName("sascha");
                                        xx.setToken(tokee);



                                        startActivity((new Intent(registerUser.this, FragmentMainActivity.class)));
                                        pg_bar.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(registerUser.this, "FAILED TO REGISTER", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                    FirebaseDatabase.getInstance().getReference("metaDateUser")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(meta).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(registerUser.this, "SUCCESS REGISTER", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else{
                    Toast.makeText(registerUser.this, "FFF", Toast.LENGTH_SHORT).show();
                    Toast.makeText(registerUser.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    public void onClick(View view) {

    }
}
