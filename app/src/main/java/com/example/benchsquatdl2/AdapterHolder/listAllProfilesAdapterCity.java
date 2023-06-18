package com.example.benchsquatdl2.AdapterHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.benchsquatdl2.AdapterHolder.listAllProfilesViewHolder;
import com.example.benchsquatdl2.R;
import com.example.benchsquatdl2.model.modelStatisticData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listAllProfilesAdapterCity extends RecyclerView.Adapter<listAllProfilesViewHolder> {

    DatabaseReference statistic;
    modelStatisticData peace;
    ArrayList<String> benchList, squatList, deadliftList, cityList;
    ArrayList<String> nameList;

    public listAllProfilesAdapterCity(ArrayList<String> benchList, ArrayList<String> squatList, ArrayList<String> deadliftList, ArrayList<String> nameList, ArrayList<String> cityList) {
        this.benchList = benchList;
        this.squatList = squatList;
        this.deadliftList = deadliftList;
        this.nameList = nameList;
        this.cityList = cityList;
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

        holder.tv_bench.setText(String.valueOf(benchList.get(position)));
        holder.tv_squat.setText(String.valueOf(squatList.get(position)));
        holder.tv_deadlift.setText(String.valueOf(deadliftList.get(position)));
        holder.tvName.setText(String.valueOf(nameList.get(position)));
        holder.countryOrState.setText("("+"City: "+ String.valueOf(cityList.get(position))+")");








    }

    @Override
    public int getItemCount() {
        return benchList.size();
    }
}
