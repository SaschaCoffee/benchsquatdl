package com.example.firebasefeedtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class squatFragment extends Fragment {

    ArrayList<greenCardModel> lstBook = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private int counter = 0;
    int arraycounter = 0;
    int sizeField =  0;
    int progr;
    private String userid;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText et_first_set, et_second_set, et_third_set, et_kg, et_kg2, et_kg3;
    private ProgressBar pgBar;
    TextView cc;
    private TextView tv, heavy1, heavy2, heavy3;
    private Button btn_add_data, btn_upload_data;
    private FirebaseUser user;
    private DatabaseReference reference,  referenceTest, referenceTraininglogPrivate, referenceTraininglogPublic,
            referenceCollectTrainingData, referenceTrainingLocation;
    private double tempCounter;
    CheckBox worldwide;
    boolean checkBoxClicked = false;
    boolean firstSet = false;
    boolean secondSet = false;
    private AlertDialog dialog;
    boolean thirdSet = false;
    boolean fourthSet = false;
    boolean fifthSet = false;
    boolean heavyChosen = false;
    int currentSelectedHeavy;


    public squatFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vx = inflater.inflate(R.layout.activitycard_squat, container, false);
        pgBar = vx.findViewById(R.id.progress_bar);
        tv = vx.findViewById(R.id.text_view_progress);
        btn_add_data = vx.findViewById(R.id.btn_add_data_card_bench);
        mRecyclerView = vx.findViewById(R.id.recyclerview_id);
        String childcard = "anzahl_squat";
        String childcardBench = "Best3BenchKg";
        String childcardDate = "Date";
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");
        referenceCollectTrainingData = FirebaseDatabase.getInstance().getReference("FeedTrainingData");
        referenceTraininglogPrivate = FirebaseDatabase.getInstance().getReference("TraininglogPrivateSquat");
        referenceTraininglogPublic = FirebaseDatabase.getInstance().getReference("TraininglogPublic");
        referenceTest = FirebaseDatabase.getInstance().getReference("teste");
        referenceTrainingLocation = FirebaseDatabase.getInstance().getReference("Location");
        userid = user.getUid();

        reference.child(userid).child(childcard).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    long model = snapshot.getValue(long.class);
                    progr = Integer.parseInt(String.valueOf(model));
                    updateCard(progr);
                    buildRecyclerView();
                } catch (Exception e) {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialogExtend xx = new dialogExtend();
        builder.setTitle("Enter Data");

        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        btn_upload_data = view.findViewById(R.id.btn_upload_data);
        et_first_set = view.findViewById(R.id.layer_two_et_symbol_reps_single_item);
        et_second_set = view.findViewById(R.id.et_symbol_reps2);
        et_third_set = view.findViewById(R.id.et_symbol_reps3);

        worldwide = view.findViewById(R.id.cb_publish_world);

        heavy1 = view.findViewById(R.id.layer_two_heavy_button_single_item);
        heavy2 = view.findViewById(R.id.heavy_button2);
        heavy3 = view.findViewById(R.id.heavy_button3);

        start();

        heavy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!heavyChosen) {
                    heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                    currentSelectedHeavy = 1;
                    heavyChosen = true;
                }
                else{
                    switch(currentSelectedHeavy){
                        case 1:
                            heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));
                            heavyChosen = false;
                            break;
                        case 2:
                            heavy2.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));
                            currentSelectedHeavy = 1;
                            heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                            heavyChosen = true;
                            break;

                        case 3:
                            heavy3.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));
                            currentSelectedHeavy = 1;
                            heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                            heavyChosen = true;
                            break;
                    }

                }
            }
        });



        heavy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!heavyChosen){
                    heavy2.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                    currentSelectedHeavy = 2;
                    heavyChosen = true;
                }
                else{
                    switch(currentSelectedHeavy){
                        case 1:
                            heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));
                            heavy2.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                            heavyChosen = true;
                            currentSelectedHeavy =  2;

                            break;
                        case 2:
                            heavy2.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));
                            currentSelectedHeavy = 2;
                            heavyChosen = false;
                            break;

                        case 3:
                            heavy3.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy));

                            currentSelectedHeavy = 2;
                            heavyChosen = true;
                            heavy2.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
                            break;
                    }

                }
            }
        });

        et_kg = view.findViewById(R.id.layer_two_et_kg_card_single_item);
        et_kg2 = view.findViewById(R.id.et_kg_card2);
        et_kg3 = view.findViewById(R.id.et_kg_card3);

        worldwide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxClicked = true;
            }
        });




        et_first_set.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                sizeField = 1;
                counter= 0;
            }
        });


        et_second_set.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                sizeField = 2;
                counter = 0;

            }
        });

        et_third_set.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                sizeField = 3;
                counter = 0;
            }
        });

        et_kg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                sizeField = 4;
                counter = 0;
            }
        });

        et_kg2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                sizeField = 5;
                counter = 0;
            }
        });

        et_kg3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                sizeField = 6;
                counter = 0;
            }
        });



        DialogInterface.OnKeyListener xxy = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                String x = "1";


                int code = keyEvent.getKeyCode();

                if(keyEvent.getAction() == KeyEvent.ACTION_UP){
                    switch (code) {
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            counter++;
                            double counterNew = 20+(counter*5);
                            tempCounter = counterNew;
                            switch(sizeField){
                                case 1:
                                    et_first_set.setText(String.valueOf(counter));
                                    break;
                                case 2:
                                    et_second_set.setText(String.valueOf(counter));
                                    break;
                                case 3:
                                    et_third_set.setText(String.valueOf(counter));
                                    break;
                                case 4:
                                    et_kg.setText(String.valueOf(counterNew));
                                    break;
                                case 5:
                                    et_kg2.setText(String.valueOf(counterNew));
                                    break;
                                case 6:
                                    et_kg3.setText(String.valueOf(counterNew));

                            }
                            return true;
                    }

                }

                if(keyEvent.getAction() == KeyEvent.ACTION_UP){
                    switch (code) {
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            counter++;
                            double counterNew = 20+(counter*5);
                            tempCounter = counterNew;
                            switch(sizeField){
                                case 1:
                                    et_first_set.setText(String.valueOf(counter));
                                    break;
                                case 2:
                                    et_second_set.setText(String.valueOf(counter));
                                    break;
                                case 3:
                                    et_third_set.setText(String.valueOf(counter));
                                    break;
                                case 4:
                                    et_kg.setText(String.valueOf(counterNew));
                                    break;
                                case 5:
                                    et_kg2.setText(String.valueOf(counterNew));
                                    break;
                                case 6:
                                    et_kg3.setText(String.valueOf(counterNew));

                            }
                            return true;
                    }

                }

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (code) {
                        case KeyEvent.KEYCODE_VOLUME_UP:

                            return true;
                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            counter--;
                            double counterNewDown = tempCounter - 1.25;
                            tempCounter = counterNewDown;

                            switch(sizeField){
                                case 1:
                                    et_first_set.setText(String.valueOf(counter));
                                    break;
                                case 2:
                                    et_second_set.setText(String.valueOf(counter));
                                    break;
                                case 3:
                                    et_third_set.setText(String.valueOf(counter));
                                    break;
                                case 4:
                                    et_kg.setText(String.valueOf(counterNewDown));
                                    break;
                                case 5:
                                    et_kg2.setText(String.valueOf(counterNewDown));
                                    break;
                                case 6:
                                    et_kg3.setText(String.valueOf(counterNewDown));

                            }
                            return true;
                    }

                }
                return true;
            }
        };

        builder.setOnKeyListener(xxy);
        createCard();
        buildRecyclerView();
        builder.setView(view);
        dialog = builder.create();

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btn_upload_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!everythingIsFilled()) {
                    if(worldwide.isChecked()) {
                        String keyTraining = referenceTraininglogPublic.push().getKey();
                        String first_set = et_first_set.getText().toString().trim();
                        String second_set = et_second_set.getText().toString().trim();
                        String third_set = et_third_set.getText().toString().trim();

                        String kg1 = et_kg.getText().toString().trim();
                        String kg2 = et_kg2.getText().toString().trim();
                        String kg3 = et_kg3.getText().toString().trim();



                        modelSquat xyz = new modelSquat(first_set, second_set, third_set, kg1, kg2, kg3);

                        referenceTraininglogPublic.child(keyTraining).setValue(xyz);
                        referenceTraininglogPrivate.child(userid).child(keyTraining).setValue(xyz);
                        referenceCollectTrainingData.child(keyTraining).child("userid").setValue(userid);
                        referenceTrainingLocation.child("Hannover").child("Datum5").setValue(keyTraining);




                        progr++;
                        reference.child(userid).child("anzahl").setValue(progr);

                        updateProgressBar(progr);
                        updateCard(progr);
                        buildRecyclerView();
                        dialog.dismiss();
                    }

                    else{
                        String keyTraining = referenceTraininglogPrivate.push().getKey();
                        String first_set = et_first_set.getText().toString().trim();
                        String second_set = et_second_set.getText().toString().trim();
                        String third_set = et_third_set.getText().toString().trim();

                        String kg1 = et_kg.getText().toString().trim();
                        String kg2 = et_kg2.getText().toString().trim();
                        String kg3 = et_kg3.getText().toString().trim();


                        modelSquat xyz = new modelSquat(first_set, second_set, third_set, kg1, kg2, kg3);

                        referenceTraininglogPrivate.child(userid).child(keyTraining).setValue(xyz);


                        progr++;
                        reference.child(userid).child("anzahl").setValue(progr);

                        updateProgressBar(progr);
                        updateCard(progr);
                        buildRecyclerView();
                        dialog.dismiss();

                    }
                }

            }
        });



        return vx;
    }
    private void start() {
        heavy1.setBackground(ContextCompat.getDrawable(getActivity(), R.mipmap.heavy_true));
        currentSelectedHeavy = 1;
        heavyChosen = true;
    }

    public boolean everythingIsFilled(){

        boolean enter_data = false;
        boolean valid_field_data = true;

        String first_set = et_first_set.getText().toString().trim();
        String second_set = et_second_set.getText().toString().trim();
        String third_set = et_third_set.getText().toString().trim();


        if(!third_set.isEmpty()) {
            if (first_set.isEmpty() || second_set.isEmpty()) {
                et_first_set.setError("Field 1 and 2 are missing");
                valid_field_data = true;
            } else {
                valid_field_data = false;
            }
        }
        else if(!second_set.isEmpty()){
            if(first_set.isEmpty()){
                valid_field_data = true;
                et_first_set.setError("Field 1 is missing");
            }
            else{
                valid_field_data = false;
            }

        }
        else{
            valid_field_data = true;
        }




        switch(currentSelectedHeavy){
            case 1:
                if(first_set.isEmpty()){
                    enter_data = true;
                    et_first_set.setError("The selected Heavy Field is wrong");
                }
                else{
                    enter_data = false;
                }
                break;
            case 2:
                if(second_set.isEmpty()){
                    et_second_set.setError("The selected Heavy Field is wrong");
                    enter_data = true;
                    et_first_set.setError("First Field is empty");
                }
                else{
                    enter_data = false;
                }
                break;
            case 3:
                if(third_set.isEmpty()){
                    enter_data = true;
                    et_third_set.setError("The selected Heavy Field is wrong");
                }
                else{
                    enter_data = false;
                }
        }
        return (enter_data || valid_field_data );



    }


    private void buildRecyclerView() {


        mRecyclerView.setHasFixedSize(true);
        mAdapter = new cardViewAdapter(getActivity(), lstBook);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void updateCard(int z) {
        int countstart = z;
        for (int x = 0; x < z; x++) {
            lstBook.set(x, new greenCardModel(R.drawable.quadratgruen));

        }

    }


    private void createCard() {
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));


        int arraySize = lstBook.size();
        updateProgressBar(arraySize);


    }

    private void updateProgressBar(int z) {
        int x = z;
        String zy = String.valueOf(z) + "%";

        pgBar.setProgress(x);
        tv.setText(zy);


    }
}