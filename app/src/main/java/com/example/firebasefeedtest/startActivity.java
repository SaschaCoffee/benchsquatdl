package com.example.firebasefeedtest;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class startActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    LoginButton facebook_login;
    private DatabaseReference reference, referenceTest,referenceTest2;
    CallbackManager mCallbackManager;
    SignInButton googleLogin;
    BeginSignInRequest signInRequest;
    SignInClient oneTapClient, oneTapClient2;
    GoogleSignInClient mGoogleClient;
    TextView loginNormal;



    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private static final int REQUEST_CODE_GOOGLE_SIGN_IN = 1;
    private boolean showOneTapUI = true;


    private static final String EMAIL = "email";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        facebook_login = findViewById(R.id.btn_facebook_login);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        referenceTest = FirebaseDatabase.getInstance().getReference();
        googleLogin = findViewById(R.id.google_login);

        loginNormal = findViewById(R.id.textView3);

        loginNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(startActivity.this, logRegActivity.class)));
            }
        });



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


        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                signIn();





            }
        });


    }

    private void signIn() {
        Toast.makeText(this, "sender", Toast.LENGTH_SHORT).show();
        GetSignInIntentRequest request =
                GetSignInIntentRequest.builder()
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .build();

        Identity.getSignInClient(this)
                .getSignInIntent(request)
                .addOnSuccessListener(
                        result -> {
                            try {

                                startIntentSenderForResult(
                                        result.getIntentSender(),
                                        REQUEST_CODE_GOOGLE_SIGN_IN,
                                        /* fillInIntent= */ null,
                                        /* flagsMask= */ 0,
                                        /* flagsValue= */ 0,
                                        /* extraFlags= */ 0,
                                        /* options= */ null);
                            } catch (IntentSender.SendIntentException e) {
                                Log.e("TAG", "Google Sign-in failed");
                            }
                        })
                .addOnFailureListener(
                        e -> {
                            Log.e("TAG", "Google Sign-in failed", e);
                        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "rq:" + requestCode, Toast.LENGTH_SHORT).show();

        switch (requestCode) {

            case REQUEST_CODE_GOOGLE_SIGN_IN:
                try {
                    SignInCredential credential = Identity.getSignInClient(this).getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();

                    if (idToken !=  null) {
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("TAG", "signInWithCredential:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            FirebaseDatabase.getInstance().getReference("googleUsers")
                                                    .child(user.getUid()).setValue("fff");


                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                                        }
                                    }
                                });




                    }
                } catch (ApiException e) {
                    // ...
                    Toast.makeText(this, "huhuhu", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    void handleFacebookAccessToken(AccessToken token) {
        Log.d("access", "komtan");
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser user = mAuth.getCurrentUser();

                            facebookSignModel fb_model = new facebookSignModel(user.getDisplayName(), user.getEmail());

                            FirebaseDatabase.getInstance().getReference("fbUsers")
                            .child(user.getUid()).setValue(fb_model);


                            startActivity((new Intent(startActivity.this, fragmentTestAct.class)));



                        } else{
                                               }
                    }
                });

    }
}


