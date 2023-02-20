package com.example.benchsquatdl2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstFragment extends Fragment  {
    public static final String COLUMN_ID = "ID";
    public static final String GERRRAWROH = "GERRRAWROH";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";

    private DataBaseHelper mDatabase;
    SQLiteDatabase db;
    DatabaseReference statistic;
    Button sbd, add, btn_logregister;
    TextView tv_banner;
    public FirstFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vx = inflater.inflate(R.layout.list, container, false);

        Button add = vx.findViewById(R.id.btnAdd);
        sbd = vx.findViewById(R.id.sbd_button);
        btn_logregister = vx.findViewById(R.id.btn_register_log);

        RecyclerView contactView = vx.findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);


        mDatabase = new DataBaseHelper(getContext());

        statistic = FirebaseDatabase.getInstance().getReference();

        statistic.child("TraininglogPrivateSquat").child("userID22").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    GenericTypeIndicator<modelBenchList> t = new GenericTypeIndicator<modelBenchList>() {
                    };


                    modelBenchList yourStringArray = snapshot.getValue(t);
                    ArrayList<modelBenchList> xx = new ArrayList<>();

                    Log.d("listSize", "" + yourStringArray.getSquat1());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        statistic.child("metaDateUser").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    String b = task.getResult().getValue().toString();
                    Log.d("papa8","mama" + b);
                }
            }
        });






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), searchActivity.class);
                startActivity(intent);
            }
        });


        return vx;
    }






}
