package com.example.benchsquatdl2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedAdapter extends RecyclerView.Adapter<FeedHolder> {

    private FirebaseUser user;
    private Context context;
    private DatabaseReference reference, referenceTest;


    public FeedAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);

        return new FeedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, int position) {
        holder.tv_feed_bench.setText("hALLO");
        holder.img_feed_user.setImageResource(R.drawable.quadratgruen);


    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
