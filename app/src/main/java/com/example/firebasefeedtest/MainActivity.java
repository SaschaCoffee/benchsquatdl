package com.example.firebasefeedtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private Button btn_register, card, btn_login;
    private Context context;
    private DatabaseReference reference, referenceTest,referenceTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView contactView = findViewById(R.id.rv_feed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);

        contactView.setVisibility(View.VISIBLE);

        FeedAdapter myAdapter = new FeedAdapter(this);
        contactView.setAdapter(myAdapter);

        btn_login = findViewById(R.id.btn_login_main);
        btn_register = findViewById(R.id.btn_register_main);
        card = findViewById(R.id.btn_card);

        modelSquat xx = new modelSquat("300", "333", "33");

        referenceTest = FirebaseDatabase.getInstance().getReference();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(MainActivity.this, registerUser.class)));
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(MainActivity.this, cardMainactivity.class)));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(MainActivity.this, logRegActivity.class)));
            }
        });



        //GREENCARD EDITING TEST
        int arraysize = 3;
        int selectIndex = 1;


        referenceTest.child("metdaDatenUser").child("UID").child("bestSquat").setValue("33");
        referenceTest.child("metdaDatenUser").child("UID").child("Follower").setValue("UID21");






















    














    }
    
    
}