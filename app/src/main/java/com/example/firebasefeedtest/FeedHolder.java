package com.example.firebasefeedtest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedHolder extends RecyclerView.ViewHolder {
    TextView tv_feed_user, tv_feed_text, tv_feed_squat, tv_feed_bench, tv_feed_deadlift;
    ImageView img_feed_user;

    public FeedHolder(@NonNull View itemView) {
        super(itemView);
        tv_feed_user = itemView.findViewById(R.id.tv_feed_username);
        tv_feed_text = itemView.findViewById(R.id.tv_feed_text);
        tv_feed_squat = itemView.findViewById(R.id.tv_feed_squat);
        tv_feed_bench = itemView.findViewById(R.id.tv_feed_bench);
        tv_feed_deadlift = itemView.findViewById(R.id.tv_feed_deadlift);
        img_feed_user = itemView.findViewById(R.id.img_user_feed);


    }
}
