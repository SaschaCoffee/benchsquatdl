package com.example.firebasefeedtest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    Context mcontext;
    Integer counter = 0;
    AlertDialog dialog;
    TextView heavy1, heavy_buttontwo , heavy3;
    boolean heavyChosen = false;
    int currentSelectedHeavy;
    String rep = "";
    String kg = "";
    dialogRepKgModel dialogRepKgModel;
    ArrayList<String> hallo;
    String firstrep;
    String firstkg;



    public ContactAdapter(Context mcontext, Integer counter, AlertDialog dialog, String firstrep, String firstkg) {
        this.mcontext = mcontext;
        this.counter = counter;
        this.dialog = dialog;
        this.firstrep = firstrep;
        this.firstkg = firstkg;

    }



    public ArrayList<String> returnMyObject(){
        hallo = new ArrayList<String>();
        if(!rep.isEmpty() && !kg.isEmpty()) {
            hallo.add(0, rep);
            hallo.add(1, kg);
            Log.d("geht", "adapterArrayListReturn"+ hallo.get(0)+ hallo.get(1));
            //hallo.add(rep);
            //hallo.add(kg);
        }
        return hallo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch(counter){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_one, parent, false);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_one, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_two, parent, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + counter);
        }

    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //holder.reps.setInputType(InputType.TYPE_CLASS_NUMBER);

        if(counter == 1){

            holder.l1_kg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.d("textWatch","before" +charSequence);
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.d("textWatch","on" +charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    Log.d("textWatch","after" +editable);
                    kg = editable.toString();
                    returnMyObject();

                }
            });

            holder.l1_reps.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    rep = editable.toString();
                    returnMyObject();
                }
            });

            holder.l1_heavy_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l1_heavy_button.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 1;
                        heavyChosen = true;
                    }
                    else{
                        holder.l1_heavy_button.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                        heavyChosen = false;
                    }

                }

            });
        }

            if(counter == 2) {

                Log.d("geht","broo" + firstrep + firstkg);

                holder.l2_reps_one.setText(firstrep);
                holder.l2_kg_one.setText(firstkg);

                holder.l2_reps_two.setText(firstrep);
                holder.l2_kg_two.setText(firstkg);

                holder.l2_heavy_button_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!heavyChosen) {
                            holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                            currentSelectedHeavy = 1;
                            heavyChosen = true;
                        } else {
                            switch (currentSelectedHeavy) {
                                case 1:
                                    holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                    heavyChosen = false;
                                    break;
                                case 2:
                                    holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                    currentSelectedHeavy = 1;
                                    holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                    heavyChosen = true;
                                    break;

                            }
                        }
                    }
                });

                holder.l2_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!heavyChosen) {
                            holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                            currentSelectedHeavy = 2;
                            heavyChosen = true;
                        } else {
                            switch (currentSelectedHeavy) {
                                case 1:
                                    holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                    holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                    currentSelectedHeavy = 2;
                                    heavyChosen = true;
                                    break;
                                case 2:
                                    holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                    heavyChosen = false;
                                    break;
                            }
                        }

                    }
                });


            }

        if(counter == 3) {

            Log.d("geht","broo" + firstrep + firstkg);

            holder.l2_reps_one.setText(firstrep);
            holder.l2_kg_one.setText(firstkg);

            holder.l2_reps_two.setText(firstrep);
            holder.l2_kg_two.setText(firstkg);


            holder.l2_heavy_button_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 1;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                            case 2:
                                holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                currentSelectedHeavy = 1;
                                holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                heavyChosen = true;
                                break;

                        }
                    }
                }
            });

            holder.l2_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 2;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l2_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                        }
                    }

                }
            });


        }





    }

    @Override
    public int getItemCount() {
        Log.d("getItemCountA","count:" +counter);
        return 1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView l1_heavy_button, l2_heavy_button_one, l2_heavy_button_two, l3_heavy_button_one, l3_heavy_button_two, l3_heavy_button_three;
        ImageView l1_pencil, l2_pencil_one, l2_pencil_two;
        EditText l1_reps, l1_kg, l2_reps_one, l2_reps_two, l2_kg_one, l2_kg_two;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        Log.d("getItemCountA","count:" +"counter");
        //LayOne, Count = 1,
        l1_heavy_button = itemView.findViewById(R.id.layer_one_heavy);
        l1_pencil = itemView.findViewById(R.id.layer_one_img);
        l1_reps = itemView.findViewById(R.id.layer_one_reps);
        l1_kg = itemView.findViewById(R.id.layer_one_kg);

        //Layer Two
        l2_heavy_button_one = itemView.findViewById(R.id.layer_two_heavy_button_single_item);
        l2_heavy_button_two = itemView.findViewById(R.id.layer_two_heavy_button_single_item_two);
        l2_pencil_one = itemView.findViewById(R.id.layer_two_image_button);
        l2_pencil_two = itemView.findViewById(R.id.layer_two_image_button_two);
        l2_reps_one = itemView.findViewById(R.id.layer_two_et_symbol_reps_single_item);
        l2_reps_two = itemView.findViewById(R.id.layer_two_et_symbol_reps_single_item_two);
        l2_kg_one = itemView.findViewById(R.id.layer_two_et_kg_card_single_item);
        l2_kg_two = itemView.findViewById(R.id.layer_two_et_kg_card_single_item_two);

        //Layer Three
            l3_heavy_button_one = itemView.findViewById(R.id.layer_two_heavy_button_single_item);
            l3_heavy_button_two = itemView.findViewById(R.id.layer_two_heavy_button_single_item_two);
            l3_heavy_button_three = itemView.findViewById(R.id.layer_two_heavy_button_single_item_two);
            l3_pencil_one = itemView.findViewById(R.id.layer_two_image_button);
            l3_pencil_two = itemView.findViewById(R.id.layer_two_image_button_two);
            l3_pencil_three =
            l3_reps_one = itemView.findViewById(R.id.layer_two_et_symbol_reps_single_item);
            l3_reps_two = itemView.findViewById(R.id.layer_two_et_symbol_reps_single_item_two);
            l3_kg_one = itemView.findViewById(R.id.layer_two_et_kg_card_single_item);
            l3_kg_two = itemView.findViewById(R.id.layer_two_et_kg_card_single_item_two);










        }
    }
}
