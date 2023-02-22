package com.example.benchsquatdl2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment  {
    public static final String COLUMN_ID = "ID";
    public static final String GERRRAWROH = "GERRRAWROH";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";

    private DataBaseHelper mDatabase;
    SQLiteDatabase db;
    RecyclerView contactView;
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
        statistic = FirebaseDatabase.getInstance().getReference();
        contactView = vx.findViewById(R.id.myContactList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);


        Log.d("getV","FirstFragment");
        ArrayList<Integer> x = new ArrayList<>();

        statistic.child("statisticData").limitToFirst(4).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<List> benchList = new ArrayList<>();
                ArrayList<List> squatList = new ArrayList<>();
                ArrayList<List> deadliftList = new ArrayList<>();

                for(DataSnapshot xx : snapshot.getChildren()){


                      GenericTypeIndicator<ArrayList<Double>> t = new GenericTypeIndicator<ArrayList<Double>>() {
                      };
                //    GenericTypeIndicator<ArrayList<String>> u = new GenericTypeIndicator<ArrayList<String>>() {
                  //  };
                      List<Double> myBench = xx.child("Best3BenchKg").getValue(t);
                      List<Double> myDeadlift = xx.child("Best3DeadliftKg").getValue(t);
                      List<Double> mySquat = xx.child("Best3SquatKg").getValue(t);
               //     ArrayList<String> name = xx.child("Name").getValue(u);
               //     buildRecyclerview(myBench);

                 benchList.add(myBench);
                 squatList.add(mySquat);
                 deadliftList.add(myDeadlift);

                //    Log.d("getMe","" + myBench.get(0));
                //    Log.d("getMe","" + name.get(0));


                    //holder.tv_bench.setText(String.valueOf(myBench.get(placeBench)));

                    //    List<String> hey = snapshot.getValue(List.class);


                    //  GenericTypeIndicator<modelBenchList> t = new GenericTypeIndicator<modelBenchList>() {
                    //  };

                    //  modelBenchList yourStringArray = xx.getValue(t);

                    //   Log.d("listSize", "" + yourStringArray.getSquat1());


                }
                buildRecyclerview(benchList,squatList,deadliftList);

            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("getV","VV" + error);

            }
        });




        Log.d("getMe","" + x.size());


        return vx;
    }

    private void buildRecyclerview(ArrayList<List> benchList, ArrayList<List> squatList, ArrayList<List> deadliftList) {
        listAllProfilesAdapter profilesAdapter = new listAllProfilesAdapter(benchList,squatList,deadliftList);

        contactView.setAdapter(profilesAdapter);
    }








}
