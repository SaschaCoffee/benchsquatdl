package com.example.benchsquatdl2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class listAllProfilesAdapter extends RecyclerView.Adapter<listAllProfilesViewHolder>{

    DatabaseReference statistic;
    modelStatisticData peace;
    ArrayList<List> benchList, squatList, deadliftList;

    public listAllProfilesAdapter(ArrayList<List> benchList, ArrayList<List> squatList, ArrayList<List> deadliftList) {
        this.benchList = benchList;
        this.squatList = squatList;
        this. deadliftList = deadliftList;
    }


    @NonNull
    @Override
    public listAllProfilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactistlayout, parent, false);


        return new listAllProfilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listAllProfilesViewHolder holder, int position) {
        statistic = FirebaseDatabase.getInstance().getReference();

        int sizeList = benchList.get(position).size();

        holder.tv_bench.setText(String.valueOf(benchList.get(position).get(sizeList - 1)));
        Log.d("getV","AA" + benchList.size());



    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
