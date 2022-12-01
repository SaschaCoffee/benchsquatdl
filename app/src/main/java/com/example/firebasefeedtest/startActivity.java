package com.example.firebasefeedtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class startActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    LoginButton facebook_login;
    private DatabaseReference reference, referenceTest,referenceTest2;
    CallbackManager mCallbackManager;

    private static final String EMAIL = "email";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        facebook_login = findViewById(R.id.btn_facebook_login);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        referenceTest = FirebaseDatabase.getInstance().getReference();

        facebook_login.setReadPermissions(Arrays.asList(EMAIL));
        facebook_login.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("reg1","ddddd");
                handleFacebookAccessToken(loginResult.getAccessToken());
                Toast.makeText(startActivity.this, "tschau", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Log.d("register1", "cancled");


            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.d("register1", "error");

            }
        });

    }




    void handleFacebookAccessToken(AccessToken token) {
        Log.d("access", "komtan");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            startActivity((new Intent(startActivity.this, MainActivity.class)));
                            FirebaseUser user = mAuth.getCurrentUser();

                            facebookSignModel fb_model = new facebookSignModel(user.getDisplayName(), user.getEmail());

                            FirebaseDatabase.getInstance().getReference("fbUsers")
                            .child(user.getUid()).setValue(fb_model);


                            startActivity((new Intent(startActivity.this, MainActivity.class)));



                        } else{
                                               }
                    }
                });

    }
}


