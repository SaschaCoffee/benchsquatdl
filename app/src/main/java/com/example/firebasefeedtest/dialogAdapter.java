package com.example.firebasefeedtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class dialogAdapter extends RecyclerView.Adapter<dialogAdapter.dialogerHolder> {

    Context mcontext;
    Integer counter;

    public dialogAdapter(Context mcontext, Integer counter) {
        this.mcontext = mcontext;
        this.counter = counter;
    }

    @NonNull
    @Override
    public dialogerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item, parent, false);
        return new dialogerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dialogerHolder holder, int position) {
        holder.kg.setText("kg");
        holder.reps.setText("vallah");
    }

    @Override
    public int getItemCount() {
        return counter;
    }


    public static class dialogerHolder extends RecyclerView.ViewHolder {

        ImageView x;
        TextView heavy;
        EditText kg, reps;

        public dialogerHolder(@NonNull View itemView) {
            super(itemView);

            reps = itemView.findViewById(R.id.layer_two_et_symbol_reps_single_item);
            kg = itemView.findViewById(R.id.layer_two_et_kg_card_single_item);

        }


    }


}