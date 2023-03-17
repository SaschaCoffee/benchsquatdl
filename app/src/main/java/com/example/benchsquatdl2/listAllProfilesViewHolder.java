package com.example.benchsquatdl2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class listAllProfilesViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, countryOrState, tv_squat, tv_bench, tv_deadlift, tv_city;
    ImageView img;



    public listAllProfilesViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.contactName);
        countryOrState = itemView.findViewById(R.id.countryOrState);
        tv_squat = itemView.findViewById(R.id.tv_squat);
        tv_bench = itemView.findViewById(R.id.tv_bench);
        tv_deadlift = itemView.findViewById(R.id.tv_deadlift);
        img = itemView.findViewById(R.id.deleteContact);

    }


}
