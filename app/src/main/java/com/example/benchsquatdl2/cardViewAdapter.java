package com.example.benchsquatdl2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class cardViewAdapter extends RecyclerView.Adapter<cardViewAdapter.MyViewHolder> {
    private ArrayList<greenCardModel> mData;
    private Context mContext;

    public interface PlayPauseClick {
        void imageButtonOnClick(View v, int position);
    }

    private PlayPauseClick callback;

    public void setPlayPauseClickListener(PlayPauseClick listener) {
        this.callback = listener;
    }




    public cardViewAdapter(Context mcontext, ArrayList<greenCardModel> mData) {
        this.mData = mData;
        this.mContext = mcontext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item_book, parent, false);
        MyViewHolder evh = new MyViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());

        holder.cardView.setClickable(true);
        holder.cardView.setFocusable(true);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("cardview","" + position);
                if (callback != null) {
                    callback.imageButtonOnClick(view, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img_book_thumbnail;
        CardView cardView;
        EditText rep1_focus,kg1_focus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_book_thumbnail  = itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }
}