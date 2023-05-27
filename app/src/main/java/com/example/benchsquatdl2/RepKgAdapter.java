package com.example.benchsquatdl2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
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

public class RepKgAdapter extends RecyclerView.Adapter<RepKgAdapter.MyViewHolder> {

    Context mcontext;
    Integer counter = 0;
    AlertDialog dialog;
    TextView heavy1, heavy_buttontwo , heavy3;
    boolean heavyChosen = false;
    int currentSelectedHeavy;
    String rep = "";
    String kg = "";
    RepKgModel RepKgModel;
    ArrayList<String> hallo;
    String firstrep;
    String firstkg;
    private int sizeField,counterr;
    private double tempCounter;
    View repKgview;

    public interface PlayPauseClick {
        void imageButtonOnClick2(View v, int position, String l1_reps);
    }

    private PlayPauseClick callback;

    public void setPlayPauseClickListener(PlayPauseClick listener) {
        this.callback = listener;
    }

    public RepKgAdapter(Context mcontext, Integer counter, AlertDialog dialog, RepKgModel RepKgModel) {
        this.mcontext = mcontext;
        this.counter = counter;
        this.dialog = dialog;
        this.RepKgModel = RepKgModel;
    }



    public ArrayList<String> returnMyObject(){
        hallo = new ArrayList<String>();
        if(!rep.isEmpty() && !kg.isEmpty() && counter == 1) {
            hallo.add(0, rep);
            hallo.add(1, kg);
            Log.d("pis","" + kg);
            hallo.get(0);
            Log.d("pis","array"+ hallo.get(0));
        }
        else {
            Log.d("emnpty","it's empty brooo" + hallo.size());
        }
         if( counter == 2){
             hallo.add(0, RepKgModel.getFirstrep().toString().trim());
             hallo.add(1, RepKgModel.getFirstkg().toString().trim());
             hallo.add(2, RepKgModel.getFirstrep().toString().trim());
             hallo.add(3, RepKgModel.getFirstkg().toString().trim());

             if(!rep.isEmpty() && !kg.isEmpty()){
                     hallo.add(0, RepKgModel.getFirstrep());
                     hallo.add(1, RepKgModel.getFirstkg());
                     hallo.add(2,rep);
                     hallo.add(3,kg);
             }
             if(!rep.isEmpty() && kg.isEmpty() ){
                 hallo.add(0, RepKgModel.getFirstrep());
                 hallo.add(1, RepKgModel.getFirstkg());
                 hallo.add(2,rep);
                 hallo.add(3, RepKgModel.getFirstkg());
             }

             if(!kg.isEmpty() && rep.isEmpty() ){
                 hallo.add(0, RepKgModel.getFirstrep());
                 hallo.add(1, RepKgModel.getFirstkg());
                 hallo.add(2, RepKgModel.getFirstrep());
                 hallo.add(3,kg);

             }



         }

        if(counter == 3){
            Log.d("geht", "ArrayList counter" + counter);
            hallo.add(0, RepKgModel.getFirstrep());
            hallo.add(1, RepKgModel.getFirstkg());
            hallo.add(2, RepKgModel.getSecondrep());
            hallo.add(3, RepKgModel.getSecondkg());
            hallo.add(4, RepKgModel.getSecondrep());
            hallo.add(5, RepKgModel.getSecondkg());

            if(!kg.isEmpty() && !rep.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, rep);
                hallo.add(5, kg);
            }

            if(!kg.isEmpty() && rep.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getSecondrep());
                hallo.add(5,kg);

            }

            if(!rep.isEmpty() && kg.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4,rep);
                hallo.add(5, RepKgModel.getSecondkg());

            }
        }

        if(counter == 4){
            Log.d("geht", "ArrayList counter" + counter);
            hallo.add(0, RepKgModel.getFirstrep());
            hallo.add(1, RepKgModel.getFirstkg());
            hallo.add(2, RepKgModel.getSecondrep());
            hallo.add(3, RepKgModel.getSecondkg());
            hallo.add(4, RepKgModel.getThirdrep());
            hallo.add(5, RepKgModel.getThirdkg());
            hallo.add(6, RepKgModel.getThirdrep());
            hallo.add(7, RepKgModel.getThirdkg());

            if(!kg.isEmpty() && !rep.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getThirdrep());
                hallo.add(5, RepKgModel.getThirdkg());
                hallo.add(6, rep);
                hallo.add(7,kg);
            }

            if(!kg.isEmpty() && rep.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getThirdrep());
                hallo.add(5, RepKgModel.getThirdkg());
                hallo.add(6, RepKgModel.getThirdrep());
                hallo.add(7,kg);

            }

            if(!rep.isEmpty() && kg.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getThirdrep());
                hallo.add(5, RepKgModel.getThirdkg());
                hallo.add(6, rep);
                hallo.add(7, RepKgModel.getThirdkg());


            }


        }

        if(counter == 5){
            Log.d("geht", "ArrayList counter" + counter);
            hallo.add(0, RepKgModel.getFirstrep());
            hallo.add(1, RepKgModel.getFirstkg());
            hallo.add(2, RepKgModel.getSecondrep());
            hallo.add(3, RepKgModel.getSecondkg());
            hallo.add(4, RepKgModel.getThirdrep());
            hallo.add(5, RepKgModel.getThirdkg());
            hallo.add(6, RepKgModel.getFourthrep());
            hallo.add(7, RepKgModel.getFourthkg());
            hallo.add(8, RepKgModel.getFourthrep());
            hallo.add(9, RepKgModel.getFourthkg());

            if(!kg.isEmpty() && rep.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getThirdrep());
                hallo.add(5, RepKgModel.getThirdkg());
                hallo.add(6, RepKgModel.getFourthrep());
                hallo.add(7, RepKgModel.getFourthkg());
                hallo.add(8, RepKgModel.getFourthrep());
                hallo.add(9, kg);

            }

            if(!rep.isEmpty() && kg.isEmpty()){
                hallo.add(0, RepKgModel.getFirstrep());
                hallo.add(1, RepKgModel.getFirstkg());
                hallo.add(2, RepKgModel.getSecondrep());
                hallo.add(3, RepKgModel.getSecondkg());
                hallo.add(4, RepKgModel.getThirdrep());
                hallo.add(5, RepKgModel.getThirdkg());
                hallo.add(6, RepKgModel.getFourthrep());
                hallo.add(7, RepKgModel.getFourthkg());
                hallo.add(8, rep);
                hallo.add(9,RepKgModel.getFourthkg());
            }

        }

        return hallo;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch(counter){
            case 0:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_one, parent, false);
            case 1:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_one, parent, false);
                break;
            case 2:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_two, parent, false);
                break;
            case 3:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_three, parent, false);
                break;
            case 4:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_fourth, parent, false);
                break;
            case 5:
                this.repKgview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dialog_item_fifth, parent, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + counter);
        }

    return new MyViewHolder(repKgview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
     dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

      //holder.l1_reps.setInputType(InputType.TYPE_CLASS_NUMBER);

        //FIRST RUN, COUNT == 1

        if(counter == 1){

      holder.l1_reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean b) {
              if (callback != null) {
                  Log.d("callback2","" + callback);
                  callback.imageButtonOnClick2(repKgview, position, "l1_reps");
              }
          }
      });

      holder.l1_reps.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Log.d("callback2","click");
          }
      });


      holder.l1_kg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean b) {
              if (callback != null) {
                  Log.d("callback2","" + callback);
                  callback.imageButtonOnClick2(repKgview, position,"l1_kg");
              }
          }
      });

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
                    Log.d("textWatchh","after" +editable);
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


                holder.l2_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!heavyChosen) {
                            holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                            currentSelectedHeavy = 1;
                            heavyChosen = true;
                        }
                        else{
                            holder.l2_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                            heavyChosen = false;
                        }
                    }

                });



                holder.l2_reps_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (callback != null) {
                            Log.d("callback2","" + callback);
                            callback.imageButtonOnClick2(repKgview, position,"l2_reps_two");
                            Log.d("position2","" + position);
                        }
                    }
                });

                holder.l2_reps_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (callback != null) {
                            Log.d("callback2","" + callback);
                            callback.imageButtonOnClick2(repKgview, position,"l2_reps_one");
                        }
                    }
                });

                holder.l2_kg_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (callback != null) {
                            Log.d("callback2","" + callback);
                            callback.imageButtonOnClick2(repKgview, position,"l2_kg_one");
                        }
                    }
                });

                holder.l2_kg_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (callback != null) {
                            Log.d("callback2","" + callback);
                            callback.imageButtonOnClick2(repKgview, position,"l2_kg_two");
                        }
                    }
                });

                Log.d("geht","broo" + firstrep + firstkg);

                holder.l2_reps_one.setText(RepKgModel.getFirstrep());
                holder.l2_kg_one.setText(RepKgModel.getFirstkg());

                holder.l2_reps_two.setText(RepKgModel.getFirstrep());
                holder.l2_kg_two.setText(RepKgModel.getFirstkg());



                holder.l2_kg_two.addTextChangedListener(new TextWatcher() {
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

                holder.l2_reps_two.addTextChangedListener(new TextWatcher() {
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

            holder.l3_kg_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_kg_one");
                    }
                }
            });

            holder.l3_kg_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_kg_two");
                    }
                }
            });

            holder.l3_kg_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_kg_three");
                    }
                }
            });

            holder.l3_reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_reps");
                    }
                }
            });

            holder.l3_reps_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_reps_two");
                    }
                }
            });

            holder.l3_reps_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l3_reps_three");
                    }
                }
            });

            Log.d("geht","broo" + firstrep + firstkg);

            holder.l3_reps.setText(RepKgModel.getFirstrep());
            holder.l3_reps_two.setText(RepKgModel.getSecondrep());
            holder.l3_reps_three.setText(RepKgModel.getSecondrep());

            holder.l3_kg_one.setText(RepKgModel.getFirstkg());
            holder.l3_kg_two.setText(RepKgModel.getSecondkg());
            holder.l3_kg_three.setText(RepKgModel.getSecondkg());




            holder.l3_kg_three.addTextChangedListener(new TextWatcher() {
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

            holder.l3_reps_three.addTextChangedListener(new TextWatcher() {
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

            holder.l3_heavy_button_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 1;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                            case 2:
                                holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                currentSelectedHeavy = 1;
                                holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                heavyChosen = true;
                                break;
                            case 3:
                                holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                currentSelectedHeavy = 1;
                                holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                heavyChosen = true;
                                break;
                        }
                    }
                }
            });

            holder.l3_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 2;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                            case 3:
                                holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;
                        }
                    }

                }
            });

            holder.l3_heavy_button_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 3;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l3_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l3_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l3_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                        }
                    }

                }
            });




        }
        if(counter == 4){

           holder.l4_kg_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_kg_one");
                   }
               }
           });

           holder.l4_kg_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_kg_two");
                   }
               }
           });

           holder.l4_kg_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_kg_three");
                   }
               }
           });

           holder.l4_kg_four.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_kg_four");
                   }
               }
           });

           holder.l4_reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_reps");
                   }
               }
           });

           holder.l4_reps_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_reps_two");
                   }
               }
           });

           holder.l4_reps_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_reps_three");
                   }
               }
           });

           holder.l4_reps_four.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View view, boolean b) {
                   if (callback != null) {
                       Log.d("callback2","" + callback);
                       callback.imageButtonOnClick2(repKgview, position,"l4_reps_four");
                   }
               }
           });



           

            holder.l4_reps.setText(RepKgModel.getFirstrep());
            holder.l4_reps_two.setText(RepKgModel.getSecondrep());
            holder.l4_reps_three.setText(RepKgModel.getThirdrep());
            holder.l4_reps_four.setText(RepKgModel.getThirdrep());

            holder.l4_kg_one.setText(RepKgModel.getFirstkg());
            holder.l4_kg_two.setText(RepKgModel.getSecondkg());
            holder.l4_kg_three.setText(RepKgModel.getThirdkg());
            holder.l4_kg_four.setText(RepKgModel.getThirdkg());

            holder.l4_kg_four.addTextChangedListener(new TextWatcher() {
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

            holder.l4_reps_four.addTextChangedListener(new TextWatcher() {
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


            holder.l4_heavy_button_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 1;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                            case 2:
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l4_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 2;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                currentSelectedHeavy = 2;
                                heavyChosen = false;
                                break;

                            case 3:
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l4_heavy_button_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 3;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                            case 4:
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l4_heavy_button_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 4;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l4_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l4_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                        }
                    }

                }
            });

        }
        if(counter == 5){



            holder.l5_reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_reps");
                    }
                }
            });

            holder.l5_reps_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_reps_two");
                    }
                }
            });

            holder.l5_reps_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_reps_three");
                    }
                }
            });

            holder.l5_reps_four.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_reps_four");
                    }
                }
            });

            holder.l5_reps_five.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_reps_five");
                    }
                }
            });

            holder.l5_kg_one.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_kg_one");
                    }
                }
            });

            holder.l5_kg_two.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_kg_two");
                    }
                }
            });

            holder.l5_kg_three.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_kg_three");
                    }
                }
            });

            holder.l5_kg_four.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_kg_four");
                    }
                }
            });

            holder.l5_kg_five.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (callback != null) {
                        Log.d("callback2","" + callback);
                        callback.imageButtonOnClick2(repKgview, position,"l5_kg_five");
                    }
                }
            });



            holder.l5_reps.setText(RepKgModel.getFirstrep());
            holder.l5_reps_two.setText(RepKgModel.getSecondrep());
            holder.l5_reps_three.setText(RepKgModel.getThirdrep());
            holder.l5_reps_four.setText(RepKgModel.getFourthrep());
            holder.l5_reps_five.setText(RepKgModel.getFourthrep());

            holder.l5_kg_one.setText(RepKgModel.getFirstkg());
            holder.l5_kg_two.setText(RepKgModel.getSecondkg());
            holder.l5_kg_three.setText(RepKgModel.getThirdkg());
            holder.l5_kg_four.setText(RepKgModel.getFourthkg());
            holder.l5_kg_five.setText(RepKgModel.getFourthkg());


            holder.l5_kg_four.addTextChangedListener(new TextWatcher() {
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

            holder.l5_reps_four.addTextChangedListener(new TextWatcher() {
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


            holder.l5_heavy_button_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 1;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;
                            case 2:
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                            case 5:
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l4_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 1;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l5_heavy_button_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 2;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                            case 3:
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;

                            case 5:
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 2;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l5_heavy_button_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 3;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                            case 4:
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                            case 5:
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 3;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l5_heavy_button_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 4;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                heavyChosen = false;
                                break;

                            case 5:
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 4;
                                heavyChosen = true;
                                break;

                        }
                    }

                }
            });

            holder.l5_heavy_button_five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!heavyChosen) {
                        holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                        currentSelectedHeavy = 5;
                        heavyChosen = true;
                    } else {
                        switch (currentSelectedHeavy) {
                            case 1:
                                holder.l5_heavy_button_one.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 5;
                                heavyChosen = true;
                                break;
                            case 2:
                                holder.l5_heavy_button_two.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 5;
                                heavyChosen = true;
                                break;

                            case 3:
                                holder.l5_heavy_button_three.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 5;
                                heavyChosen = true;
                                break;

                            case 4:
                                holder.l5_heavy_button_four.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy_true));
                                currentSelectedHeavy = 5;
                                heavyChosen = true;
                                break;

                            case 5:
                                holder.l5_heavy_button_five.setBackground(ContextCompat.getDrawable(mcontext, R.mipmap.heavy));
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

        TextView l1_heavy_button, l2_heavy_button_one, l2_heavy_button_two,
                l3_heavy_button_one, l3_heavy_button_two, l3_heavy_button_three
                ,l4_heavy_button_one,l4_heavy_button_two,l4_heavy_button_three,l4_heavy_button_four,
                l5_heavy_button_one,l5_heavy_button_two,l5_heavy_button_three,l5_heavy_button_four,
                l5_heavy_button_five;

        ImageView l1_pencil, l2_pencil_one, l2_pencil_two, l3_pencil, l3_pencil_two, l3_pencil_three,
                l4_pencil,l4_pencil_two,l4_pencil_three, l4_pencil_four,l5_pencil,
                l5_pencil_two,l5_pencil_three,l5_pencil_four,l5_pencile_five;

        EditText l1_reps, l1_kg, l2_reps_one, l2_reps_two, l2_kg_one, l2_kg_two, l3_reps, l3_reps_two, l3_reps_three
                ,l3_kg_one, l3_kg_two, l3_kg_three, l4_reps, l4_reps_two, l4_reps_three, l4_reps_four,
                 l4_kg_one, l4_kg_two, l4_kg_three,l4_kg_four, l5_reps, l5_reps_two, l5_reps_three, l5_reps_five,
                l5_reps_four, l5_kg_one, l5_kg_two, l5_kg_three, l5_kg_four, l5_kg_five;


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
            l3_heavy_button_one = itemView.findViewById(R.id.layer_four_heavy_button_single_item);
            l3_heavy_button_two = itemView.findViewById(R.id.layer_three_heavy_button_single_item_two);
            l3_heavy_button_three = itemView.findViewById(R.id.layer_three_heavy_button_single_item_three);

            l3_pencil = itemView.findViewById(R.id.layer_three_image_button);
            l3_pencil_two = itemView.findViewById(R.id.layer_three_image_button_two);
            l3_pencil_three = itemView.findViewById(R.id.layer_three_image_button_three);

            l3_reps = itemView.findViewById(R.id.layer_three_et_symbol_reps_single_item);
            l3_reps_two = itemView.findViewById(R.id.layer_three_et_symbol_reps_single_item_two);
            l3_reps_three = itemView.findViewById(R.id.layer_three_et_symbol_reps_single_item_two_three);

            l3_kg_one = itemView.findViewById(R.id.layer_three_et_kg_card_single_item);
            l3_kg_two = itemView.findViewById(R.id.layer_three_et_kg_card_single_item_two);
            l3_kg_three = itemView.findViewById(R.id.layer_three_et_kg_card_single_item_three);

            //Layer Four
            l4_heavy_button_one = itemView.findViewById(R.id.layer_four_heavy_button_single_item);
            l4_heavy_button_two = itemView.findViewById(R.id.layer_four_heavy_button_single_item_two);
            l4_heavy_button_three = itemView.findViewById(R.id.layer_four_heavy_button_single_item_three);
            l4_heavy_button_four = itemView.findViewById(R.id.layer_four_heavy_button_single_item_four);

            l4_pencil = itemView.findViewById(R.id.layer_four_image_button);
            l4_pencil_two = itemView.findViewById(R.id.layer_four_image_button_two);
            l4_pencil_three = itemView.findViewById(R.id.layer_four_image_button_three);
            l4_pencil_four = itemView.findViewById(R.id.layer_four_image_button_four);

            l4_reps = itemView.findViewById(R.id.layer_four_et_symbol_reps_single_item);
            l4_reps_two = itemView.findViewById(R.id.layer_four_et_symbol_reps_single_item_two);
            l4_reps_three = itemView.findViewById(R.id.layer_four_et_symbol_reps_single_item_three);
            l4_reps_four = itemView.findViewById(R.id.layer_four_et_symbol_reps_single_item_four);

            l4_kg_one = itemView.findViewById(R.id.layer_four_et_kg_card_single_item);
            l4_kg_two = itemView.findViewById(R.id.layer_four_et_kg_card_single_item_two);
            l4_kg_three = itemView.findViewById(R.id.layer_four_et_kg_card_single_item_three);
            l4_kg_four =  itemView.findViewById(R.id.layer_four_et_kg_card_single_item_four);

            //Layer five
            l5_heavy_button_one = itemView.findViewById(R.id.layer_five_heavy_button_single_item);
            l5_heavy_button_two = itemView.findViewById(R.id.layer_five_heavy_button_single_item_two);
            l5_heavy_button_three = itemView.findViewById(R.id.layer_five_heavy_button_single_item_three);
            l5_heavy_button_four = itemView.findViewById(R.id.layer_five_heavy_button_single_item_four);
            l5_heavy_button_five = itemView.findViewById(R.id.layer_five_heavy_button_single_item_five);

            l5_pencil = itemView.findViewById(R.id.layer_five_image_button);
            l5_pencil_two = itemView.findViewById(R.id.layer_five_image_button_two);
            l5_pencil_three = itemView.findViewById(R.id.layer_five_image_button_three);
            l5_pencil_four = itemView.findViewById(R.id.layer_five_image_button_four);
            l5_pencile_five = itemView.findViewById(R.id.layer_five_image_button_five);

            l5_reps = itemView.findViewById(R.id.layer_five_et_symbol_reps_single_item);
            l5_reps_two = itemView.findViewById(R.id.layer_five_et_symbol_reps_single_item_two);
            l5_reps_three = itemView.findViewById(R.id.layer_five_et_symbol_reps_single_item_three);
            l5_reps_four = itemView.findViewById(R.id.layer_five_et_symbol_reps_single_item_four);
            l5_reps_five = itemView.findViewById(R.id.layer_five_et_symbol_reps_single_item_five);

            l5_kg_one = itemView.findViewById(R.id.layer_five_et_kg_card_single_item);
            l5_kg_two = itemView.findViewById(R.id.layer_five_et_kg_card_single_item_two);
            l5_kg_three = itemView.findViewById(R.id.layer_five_et_kg_card_single_item_three);
            l5_kg_four =  itemView.findViewById(R.id.layer_five_et_kg_card_single_item_four);
            l5_kg_five = itemView.findViewById(R.id.layer_five_et_kg_card_single_item_five);









        }
    }
}
