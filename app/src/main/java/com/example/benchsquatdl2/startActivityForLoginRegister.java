package com.example.benchsquatdl2;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.benchsquatdl2.Fragment.FragmentMainActivity;
import com.example.benchsquatdl2.model.modelLogRegister.facebookSignModel;
import com.example.benchsquatdl2.model.modelApi.userModelApi;
import com.example.benchsquatdl2.model.modelSpringBoot.Customer;
import com.example.benchsquatdl2.model.modelSpringBoot.OrderRequest;
import com.example.benchsquatdl2.model.modelSpringBoot.Product;
import com.example.benchsquatdl2.model.orderResponse;
import com.example.benchsquatdl2.retrofit.UserApi;
import com.example.benchsquatdl2.retrofit.RetrofitService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class startActivityForLoginRegister extends AppCompatActivity {
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
    DatabaseReference palo;



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




        try{

        int x = Integer.parseInt(mAuth.getUid().toString());
        startActivity((new Intent(startActivityForLoginRegister.this, FragmentMainActivity.class)));
        }catch(Exception e){

        }



        loginNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity((new Intent(startActivityForLoginRegister.this, loggingActivity.class)));
            }
        });



        facebook_login.setReadPermissions(Arrays.asList(EMAIL));
        facebook_login.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookAccessToken(loginResult.getAccessToken());

                Toast.makeText(startActivityForLoginRegister.this, "tschau", Toast.LENGTH_SHORT).show();
                //startActivity((new Intent(startActivityForLoginRegister.this, FragmentMainActivity.class)));

            }

            @Override
            public void onCancel() {



            }

            @Override
            public void onError(@NonNull FacebookException e) {


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

                            }
                        })
                .addOnFailureListener(
                        e -> {

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

                                            FirebaseUser user = mAuth.getCurrentUser();

                                            FirebaseDatabase.getInstance().getReference("googleUsers")
                                                    .child(user.getUid()).setValue("fff");

                                            startActivity((new Intent(startActivityForLoginRegister.this, FragmentMainActivity.class)));


                                        } else {
                                            // If sign in fails, display a message to the user.


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

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){



                            FirebaseUser user = mAuth.getCurrentUser();


                            facebookSignModel fb_model = new facebookSignModel(user.getDisplayName(), user.getEmail());

                            palo = FirebaseDatabase.getInstance().getReference();

                            palo.child("fbUers").child(user.getUid()).setValue(fb_model);

                            RetrofitService retrofitService = new RetrofitService();
                            UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);


                            String name = user.getDisplayName();

                            String tokee = user.getUid();
                            userModelApi xx = new userModelApi();
                            xx.setName(name);
                            xx.setToken(tokee);






                            startActivity((new Intent(startActivityForLoginRegister.this, FragmentMainActivity.class)));



                        } else{


                                               }
                    }
                });

    }
}


