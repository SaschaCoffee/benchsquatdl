package com.example.firebasefeedtest;

//Anleitung
//
//Der User drückt auf "Bench" -> Fenster wird geöffnet
//und dann wird Objekt namens mAdapter von RepKgAdapter erstellt.
//Diese enthält getActivity, Counter = 1, dialog, RepKgModel.
//Dann wird es in array namens save (ArrayList<RepKgAdapter> save) gespeichert (wir sind noch immer in Benchfragment).
//-> save.add(0, mAdapter)
//
//
//Beim Eingabefeld wird rep,kg von RepKgAdapter getriggert,
//und die Variablen rep,kg mit werten gefüllt,
//wegen TextListener on Change.
//
//public ArrayList<String> returnMyObject(){
//        hallo = new ArrayList<String>();
//
//if(!rep.isEmpty() && !kg.isEmpty() && counter == 1) {
//            hallo.add(0, rep);
//            hallo.add(1, kg);
//        }
//return hallo;
//
//Dann drückt der user auf add (wir sind jz in benchfragment.java,
//und wichtig: globale variabel ->  ArrayList<RepKgAdapter> save = new ArrayList<>(); )
//
//-> RepKgModel wird initialisiert
//mit save.get(0).rMyObj.get(0)
//
//-> RepKgAdapter mAdapter_two = new RepKgAdapter(getActivity(), counter = 2,dialog, RepKgModel);
//
//-> RepKgAdapter
//public ArrayList<String> returnMyObject(){
//
//if (counter == 2)
//hallo.add(0, RepKgModel.getFirstrep().toString().trim());
//hallo.add(1, RepKgModel.getFirstkg().toString().trim());
//hallo.add(2, RepKgModel.getFirstrep().toString().trim());
//hallo.add(3, RepKgModel.getFirstkg().toString().trim());
//
//}
//
//public void onBindViewHolder(){
//
//holder.l2_reps_one.setText(RepKgModel.getFirstrep());
//holder.l2_kg_one.setText(RepKgModel.getFirstkg());
//holder.l2_reps_two.setText(RepKgModel.getFirstrep());
//holder.l2_kg_two.setText(RepKgModel.getFirstkg());
//
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class benchFragment extends Fragment implements
        cardViewAdapter.PlayPauseClick  {
    Context mcontext;

    ArrayList<greenCardModel> lstBook = new ArrayList<>();
    private FirebaseUser user;
    private DatabaseReference reference,  referenceTest, referenceTraininglogPrivate, referenceTraininglogPublic,
            referenceCollectTrainingData, referenceTrainingLocation;
    ArrayList<RepKgModel> dialogArrayReference = new ArrayList<>();
    ArrayList<Long> firebaseArray;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar pgBar;
    private TextView tv, heavy1, heavy2, heavy3;
    private EditText et_first_set, et_second_set, et_third_set, et_kg, et_kg2, et_kg3;
    int progr;
    private Button btn_add_data,upload_fb, btn_upload_data, btn_addtoTv, btntoTv, bre, btn_delete_data;
    private AlertDialog dialog;
    private String userid;
    private int counter = 0;
    int arraycounter = 0;
    int sizeField =  0;
    int newCounter = 0;
    private double tempCounter;
    CheckBox worldwide;
    boolean checkBoxClicked = false;
    boolean firstSet = false;
    boolean secondSet = false;
    boolean thirdSet = false;
    boolean fourthSet = false;
    boolean fifthSet = false;
    boolean heavyChosen = false;
    int currentSelectedHeavy;
    int count = 0;
    RepKgModel RepKgModel;
    int arraySizeContactAdapter;
    String firstrep,firstset;
    ArrayList<RepKgAdapter> save = new ArrayList<>();
    AlertDialog.Builder builder;

    String adapterCallBackString = "l1_reps";




    public benchFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View vx = inflater.inflate(R.layout.activitycard_bench, container, false);
        pgBar = vx.findViewById(R.id.progress_bar);
        tv = vx.findViewById(R.id.text_view_progress);



        referenceTraininglogPublic = FirebaseDatabase.getInstance().getReference("TraininglogPublic");

        mRecyclerView = vx.findViewById(R.id.recyclerview_id);
        String childcard = "anzahlBench";
        String childcardBench = "Best3BenchKg";
        String childcardDate = "Date";
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");
        referenceCollectTrainingData = FirebaseDatabase.getInstance().getReference("FeedTrainingData");
        referenceTraininglogPrivate = FirebaseDatabase.getInstance().getReference("TraininglogPrivateBench");
        referenceTraininglogPublic = FirebaseDatabase.getInstance().getReference("TraininglogPublic");
        referenceTest = FirebaseDatabase.getInstance().getReference("teste");
        referenceTrainingLocation = FirebaseDatabase.getInstance().getReference("Location");
        userid = user.getUid();

        bre = vx.findViewById(R.id.btn_add_data_card_bench);

        createCard();
        buildRecyclerView();

        reference.child(user.getUid()).child(childcard).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    long model = snapshot.getValue(long.class);
                    progr = Integer.parseInt(String.valueOf(model));
                    Log.d("cardMe","value:" + progr);
                    updateCard(progr);
                    buildRecyclerView();
                } catch (Exception e) {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        bre.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Log.d("sizeCase23", "countnull" + save.size());

                //OPEN DIALOG
                builder = new AlertDialog.Builder(getActivity());
                dialogExtend xx = new dialogExtend();
                builder.setTitle("Enter Data");

                //FIRST RUN, COUNT == 1, ARRAY SIZE IN CONTACTADAPTER NULL
                View vieww = getLayoutInflater().inflate(R.layout.custom_dialog_list_recyclerview, null);
                RecyclerView contactView = vieww.findViewById(R.id.rv_customdialog);

                Context emptyContext;

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                contactView.setLayoutManager(linearLayoutManager);
                contactView.setHasFixedSize(true);

                //LayOne, Count = 1,






                DialogInterface.OnKeyListener xxy = new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        String x = "1";

                        EditText l1_reps, l1_kg, l2_reps_one, l2_reps_two,
                                l2_kg_one, l2_kg_two, l3_heavy_button_one,
                                l3_heavy_button_two, l3_heavy_button_three,
                                l3_pencil, l3_pencil_two, l3_pencil_three, l3_reps,
                                l3_reps_two, l3_reps_three, l3_kg_one, l3_kg_two,
                                l3_kg_three, l4_reps, l4_reps_two, l4_reps_three,
                                l4_reps_four, l4_kg_one, l4_kg_two, l4_kg_three,
                                l4_kg_four,l5_reps, l5_reps_two, l5_reps_three,
                                l5_reps_four, l5_reps_five, l5_kg_one, l5_kg_two, l5_kg_three,
                                l5_kg_four, l5_kg_five;


                        l1_reps = vieww.findViewById(R.id.layer_one_reps);
                        l1_kg = vieww.findViewById(R.id.layer_one_kg);

                        //Layer Two
                        l2_reps_one = vieww.findViewById(R.id.layer_two_et_symbol_reps_single_item);
                        l2_reps_two = vieww.findViewById(R.id.layer_two_et_symbol_reps_single_item_two);
                        l2_kg_one = vieww.findViewById(R.id.layer_two_et_kg_card_single_item);
                        l2_kg_two = vieww.findViewById(R.id.layer_two_et_kg_card_single_item_two);

                        //Layer Three
                        l3_reps = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item);
                        l3_reps_two = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two);
                        l3_reps_three = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two_three);

                        l3_kg_one = vieww.findViewById(R.id.layer_three_et_kg_card_single_item);
                        l3_kg_two = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_two);
                        l3_kg_three = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_three);

                        //Layer Four
                        l4_reps = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item);
                        l4_reps_two = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_two);
                        l4_reps_three = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_three);
                        l4_reps_four = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_four);

                        l4_kg_one = vieww.findViewById(R.id.layer_four_et_kg_card_single_item);
                        l4_kg_two = vieww.findViewById(R.id.layer_four_et_kg_card_single_item_two);
                        l4_kg_three = vieww.findViewById(R.id.layer_four_et_kg_card_single_item_three);
                        l4_kg_four =  vieww.findViewById(R.id.layer_four_et_kg_card_single_item_four);


                        //Layer five
                        l5_reps = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item);
                        l5_reps_two = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_two);
                        l5_reps_three = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_three);
                        l5_reps_four = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_four);
                        l5_reps_five = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_five);

                        l5_kg_one = vieww.findViewById(R.id.layer_five_et_kg_card_single_item);
                        l5_kg_two = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_two);
                        l5_kg_three = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_three);
                        l5_kg_four =  vieww.findViewById(R.id.layer_five_et_kg_card_single_item_four);
                        l5_kg_five = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_five);



                        int code = keyEvent.getKeyCode();



                        if(keyEvent.getAction() == KeyEvent.ACTION_UP){


                            switch (code) {
                                case KeyEvent.KEYCODE_VOLUME_UP:
                                    counter++;
                                    int counterNew = 20+(counter*5);
                                    tempCounter = counterNew;
                                    switch(adapterCallBackString){
                                        case "l1_reps":
                                            l1_reps.setText(String.valueOf(counter));
                                            break;
                                        case "l1_kg":
                                            l1_kg.setText(String.valueOf(counterNew));
                                            break;
                                        case "l2_reps_one":
                                            int txt_rep_l2 = Integer.parseInt(l2_reps_one.getText().toString().trim());
                                            Log.d("txt","mem" + txt_rep_l2);
                                            l2_reps_one.setText(String.valueOf(1 + txt_rep_l2));
                                            break;
                                        case "l2_reps_two":
                                            int txt_rep2_l2 = Integer.parseInt(l2_reps_two.getText().toString().trim());
                                            l2_reps_two.setText(String.valueOf(1 + txt_rep2_l2));
                                            break;
                                        case "l2_kg_one":
                                            int txt_kg_l2 = Integer.parseInt(l2_kg_one.getText().toString().trim());
                                            l2_kg_one.setText(String.valueOf(5 + txt_kg_l2));
                                            break;
                                        case "l2_kg_two":
                                            int txt_kg2_l2 = Integer.parseInt(l2_kg_two.getText().toString().trim());
                                            l2_kg_two.setText(String.valueOf(5 + txt_kg2_l2));
                                            break;


                                        case "l3_kg_one":
                                            int txt_kg_l3 = Integer.parseInt(l3_kg_one.getText().toString().trim());
                                            l3_kg_one.setText(String.valueOf(5 + txt_kg_l3));
                                            break;
                                        case "l3_kg_two":
                                            int txt_kg2_l3 = Integer.parseInt(l3_kg_two.getText().toString().trim());
                                            l3_kg_two.setText(String.valueOf(5 + txt_kg2_l3));
                                            break;
                                        case "l3_kg_three":
                                            int txt_kg3_l3 = Integer.parseInt(l3_kg_three.getText().toString().trim());
                                            l3_kg_three.setText(String.valueOf(5 + txt_kg3_l3));
                                            break;

                                        case "l3_reps":
                                            int txt_rep_l3 = Integer.parseInt(l3_reps.getText().toString().trim());
                                            l3_reps.setText(String.valueOf(1 + txt_rep_l3));
                                            break;
                                        case "l3_reps_two":
                                            int txt_rep2_l3 = Integer.parseInt(l3_reps_two.getText().toString().trim());
                                            l3_reps_two.setText(String.valueOf(txt_rep2_l3 + 1));
                                            break;
                                        case "l3_reps_three":
                                            int txt_rep3_l3 = Integer.parseInt(l3_reps_three.getText().toString().trim());
                                            l3_reps_three.setText(String.valueOf(1 + txt_rep3_l3));
                                            break;

                                        case "l4_kg_one":
                                            int txt_kg_l4 = Integer.parseInt(l4_kg_one.getText().toString().trim());
                                            l4_kg_one.setText(String.valueOf(5 + txt_kg_l4));
                                            break;
                                        case "l4_kg_two":
                                            int txt_kg2_l4 = Integer.parseInt(l4_kg_two.getText().toString().trim());
                                            l4_kg_two.setText(String.valueOf(5 + txt_kg2_l4));
                                            break;
                                        case "l4_kg_three":
                                            int txt_kg3_l4 = Integer.parseInt(l4_kg_three.getText().toString().trim());
                                            l4_kg_three.setText(String.valueOf(5 + txt_kg3_l4));
                                            break;
                                        case "l4_kg_four":
                                            int txt_kg4_l4 = Integer.parseInt(l4_kg_four.getText().toString().trim());
                                            l4_kg_four.setText(String.valueOf(5 + txt_kg4_l4));
                                            break;

                                        case "l4_reps":
                                            int txt_rep_l4 = Integer.parseInt(l4_reps.getText().toString().trim());
                                            l4_reps.setText(String.valueOf(1 + txt_rep_l4));
                                            break;
                                        case "l4_reps_two":
                                            int txt_rep2_l4 = Integer.parseInt(l4_reps_two.getText().toString().trim());
                                            l4_reps_two.setText(String.valueOf(txt_rep2_l4 + 1));
                                            break;
                                        case "l4_reps_three":
                                            int txt_rep3_l4 = Integer.parseInt(l4_reps_three.getText().toString().trim());
                                            l4_reps_three.setText(String.valueOf(txt_rep3_l4 + 1));
                                            break;
                                        case "l4_reps_four":
                                            int txt_rep4_l4 = Integer.parseInt(l4_reps_four.getText().toString().trim());
                                            l4_reps_four.setText(String.valueOf(txt_rep4_l4 + 1));
                                            break;

                                        case "l5_reps":
                                            int txt_rep_l5 = Integer.parseInt(l5_reps.getText().toString().trim());
                                            l5_reps.setText(String.valueOf(1 + txt_rep_l5));
                                            break;
                                        case "l5_reps_two":
                                            int txt_rep2_l5 = Integer.parseInt(l5_reps_two.getText().toString().trim());
                                            l5_reps_two.setText(String.valueOf(1 + txt_rep2_l5));
                                            break;
                                        case "l5_reps_three":
                                            int txt_rep3_l5 = Integer.parseInt(l5_reps_three.getText().toString().trim());
                                            l5_reps_three.setText(String.valueOf(1 + txt_rep3_l5));
                                            break;
                                        case "l5_reps_four":
                                            int txt_rep4_l5 = Integer.parseInt(l5_reps_four.getText().toString().trim());
                                            l5_reps_four.setText(String.valueOf(1 + txt_rep4_l5));
                                            break;
                                        case "l5_reps_five":
                                            int txt_rep5_l5 = Integer.parseInt(l5_reps_five.getText().toString().trim());
                                            l5_reps_five.setText(String.valueOf(1 + txt_rep5_l5));
                                            break;

                                        case "l5_kg_one":
                                            int txt_kg_l5 = Integer.parseInt(l5_kg_one.getText().toString().trim());
                                            l5_kg_one.setText(String.valueOf(5 + txt_kg_l5));
                                            break;
                                        case "l5_kg_two":
                                            int txt_kg2_l5 = Integer.parseInt(l5_kg_two.getText().toString().trim());
                                            l5_kg_two.setText(String.valueOf(5 + txt_kg2_l5));
                                            break;
                                        case "l5_kg_three":
                                            int txt_kg3_l5 = Integer.parseInt(l5_kg_three.getText().toString().trim());
                                            l5_kg_three.setText(String.valueOf(5 + txt_kg3_l5));
                                            break;
                                        case "l5_kg_four":
                                            int txt_kg4_l5 = Integer.parseInt(l5_kg_four.getText().toString().trim());
                                            l5_kg_four.setText(String.valueOf(5 +txt_kg4_l5));
                                            break;
                                        case "l5_kg_five":
                                            int txt_kg5_l5 = Integer.parseInt(l5_kg_five.getText().toString().trim());
                                            l5_kg_five.setText(String.valueOf(5 + txt_kg5_l5));
                                            break;
                                    }
                                    return true;
                            }

                        }

                        {


                        }

                        return true;
                    }
                };

                builder.setOnKeyListener(xxy);


                builder.setView(vieww);

                btn_add_data = vieww.findViewById(R.id.btn_add_rv);
                btn_upload_data = vieww.findViewById(R.id.upload_button);
                btn_delete_data = vieww.findViewById(R.id.delete_btn_rv);
                et_first_set = vieww.findViewById(R.id.layer_one_reps);
                et_kg = vieww.findViewById(R.id.layer_one_kg);
                dialog = builder.create();
                dialog.show();

                dialog.create();


                contactView.setVisibility(View.VISIBLE);
                RepKgAdapter mAdapter = new RepKgAdapter(getActivity(), 1, dialog, RepKgModel);
                save.add(0,mAdapter);
                contactView.setAdapter(save.get(0));
                mAdapter.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                String keyTraining = referenceTraininglogPublic.push().getKey();


                btn_upload_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("savesize","peng" + save.size());
                        switch(save.size()){

                            case 1:
                                EditText first_kg = vieww.findViewById(R.id.layer_one_kg);
                                EditText first_rep = vieww.findViewById(R.id.layer_one_reps);

                                String first_kg_string = first_kg.getText().toString().trim();
                                String first_rep_string = first_rep.getText().toString().trim();

                                if(!first_kg_string.isEmpty() && !first_rep_string.isEmpty()) {
                                    modelBench bench = new modelBench(first_rep_string, first_kg_string);
                                    referenceTraininglogPublic.child(keyTraining).setValue(bench);

                                    progr++;

                                    reference.child(userid).child(childcard).setValue(progr);

                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();

                                    dialog.cancel();
                                    //RESET
                                    count = 0;

                                    save.remove(0);

                                }
                                else{
                                    Toast.makeText(dialog.getContext(), "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show();
                                    Log.d("emptyy","fd");
                                }
                                break;

                            case 2:
                                EditText first_kg_layer2 = vieww.findViewById(R.id.layer_two_et_kg_card_single_item);
                                EditText first_rep_layer2 = vieww.findViewById(R.id.layer_two_et_symbol_reps_single_item);
                                EditText second_kg_layer2 = vieww.findViewById(R.id.layer_two_et_kg_card_single_item_two);
                                EditText second_rep_layer2 = vieww.findViewById(R.id.layer_two_et_symbol_reps_single_item_two);

                                String first_kg_l2 = first_kg_layer2.getText().toString().trim();
                                String second_kg_l2 = second_kg_layer2.getText().toString().trim();
                                String first_rep_l2 = first_rep_layer2.getText().toString().trim();
                                String second_rep_l2 = second_rep_layer2.getText().toString().trim();

                                if(!first_kg_l2.isEmpty() && !second_kg_l2.isEmpty() && !first_rep_l2.isEmpty() &&
                                        !second_rep_l2.isEmpty()) {

                                    modelBench bench2 = new modelBench(first_rep_l2, second_rep_l2, first_kg_l2, second_kg_l2);
                                    referenceTraininglogPublic.child(keyTraining).setValue(bench2);
                                    dialog.cancel();
                                    //RESET
                                    count = 0;
                                    save.clear();



                                }
                                else{
                                    Toast.makeText(getActivity(), "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                EditText first_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item);
                                EditText second_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_two);
                                EditText third_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_three);

                                EditText first_rep_layer3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item);
                                EditText second_rep_layer3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two);
                                EditText third_rep_layer3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two_three);

                                String first_kg_l3 = first_kg_layer3.getText().toString().trim();
                                String second_kg_l3 = second_kg_layer3.getText().toString().trim();
                                String third_kg_l3 = third_kg_layer3.getText().toString().trim();

                                String first_rep_l3 = first_rep_layer3.getText().toString().trim();
                                String second_rep_l3 = second_rep_layer3.getText().toString().trim();
                                String third_rep_l3 = third_rep_layer3.getText().toString().trim();

                                if(!first_rep_l3.isEmpty() && !second_rep_l3.isEmpty() &&
                                        !third_rep_l3.isEmpty() && !first_kg_l3.isEmpty() && !second_kg_l3.isEmpty() && !third_kg_l3.isEmpty()){
                                modelBench bench3 = new modelBench(first_rep_l3, second_rep_l3, third_rep_l3, first_kg_l3, second_kg_l3, third_kg_l3
                                );
                                referenceTraininglogPublic.child(keyTraining).setValue(bench3);
                                dialog.cancel();

                                //RESET
                                count = 0;
                                save.clear();

                            }
                                else{
                                    Toast.makeText(getActivity(), "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case 4:
                                EditText first_kg_layer4 = vieww.findViewById(R.id.layer_four_et_kg_card_single_item);
                                EditText second_kg_layer4 = vieww.findViewById(R.id.layer_four_et_kg_card_single_item_two);
                                EditText third_kg_layer4 = vieww.findViewById(R.id.layer_four_et_kg_card_single_item_three);
                                EditText fourth_kg_layer4 = vieww.findViewById(R.id.layer_four_et_kg_card_single_item_four);

                                EditText first_rep_l4 = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item);
                                EditText second_rep_l4 = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_two);
                                EditText third_rep_l4 = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_three);
                                EditText fourth_rep_l4 = vieww.findViewById(R.id.layer_four_et_symbol_reps_single_item_four);

                                String rp1_l4 = first_rep_l4.getText().toString().trim();
                                String rp2_l4 = second_rep_l4.getText().toString().trim();
                                String rp3_l4 = third_rep_l4.getText().toString().trim();
                                String rp4_l4 = fourth_rep_l4.getText().toString().trim();

                                String kg1_l4 = first_kg_layer4.getText().toString().trim();
                                String kg2_l4 = second_kg_layer4.getText().toString().trim();
                                String kg3_l4 = third_kg_layer4.getText().toString().trim();
                                String kg4_l4 = fourth_kg_layer4.getText().toString().trim();

                                if(!rp1_l4.isEmpty() && !rp2_l4.isEmpty() && !rp3_l4.isEmpty() && !rp4_l4.isEmpty()
                                && !kg1_l4.isEmpty() && !kg2_l4.isEmpty() && !kg3_l4.isEmpty() && !kg4_l4.isEmpty()) {

                                    modelBench bench4 = new modelBench(rp1_l4, rp2_l4, rp3_l4, rp4_l4, kg1_l4, kg2_l4, kg3_l4, kg4_l4);
                                    referenceTraininglogPublic.child(keyTraining).setValue(bench4);
                                    dialog.cancel();
                                    //RESET
                                    count = 0;
                                    save.clear();


                                }

                                else{
                                        Toast.makeText(getActivity(), "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show();
                                }


                                break;

                            case 5:
                                EditText first_kg_layer5 = vieww.findViewById(R.id.layer_five_et_kg_card_single_item);
                                EditText second_kg_layer5 = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_two);
                                EditText third_kg_layer5 = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_three);
                                EditText fourth_kg_layer5 = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_four);
                                EditText fifth_kg_layer5 = vieww.findViewById(R.id.layer_five_et_kg_card_single_item_five);

                                EditText first_rep_l5 = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item);
                                EditText second_rep_l5 = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_two);
                                EditText third_rep_l5 = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_three);
                                EditText fourth_rep_l5 = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_four);
                                EditText fifth_rep_l5 = vieww.findViewById(R.id.layer_five_et_symbol_reps_single_item_five);


                                String rp1_l5 = first_rep_l5.getText().toString().trim();
                                String rp2_l5 = second_rep_l5.getText().toString().trim();
                                String rp3_l5 = third_rep_l5.getText().toString().trim();
                                String rp4_l5 = fourth_rep_l5.getText().toString().trim();
                                String rp5_l5 = fifth_rep_l5.getText().toString().trim();

                                String kg1_l5 = first_kg_layer5.getText().toString().trim();
                                String kg2_l5 = second_kg_layer5.getText().toString().trim();
                                String kg3_l5 = third_kg_layer5.getText().toString().trim();
                                String kg4_l5 = fourth_kg_layer5.getText().toString().trim();
                                String kg5_l5 = fifth_kg_layer5.getText().toString().trim();

                                if(!rp1_l5.isEmpty() && !rp2_l5.isEmpty() && !rp3_l5.isEmpty() && !rp4_l5.isEmpty() && !rp5_l5.isEmpty() &&
                                        !kg1_l5.isEmpty() && !kg2_l5.isEmpty() && !kg3_l5.isEmpty() &&
                                        !kg4_l5.isEmpty() && !kg5_l5.isEmpty()){

                                modelBench bench5 = new modelBench(rp1_l5, rp2_l5, rp3_l5, rp4_l5, rp5_l5, kg1_l5, kg2_l5, kg3_l5, kg4_l5, kg5_l5);

                                referenceTraininglogPublic.child(keyTraining).setValue(bench5);
                                dialog.cancel();
                                //RESET
                                count = 0;

                            }
                                break;
                        }
                    }
                });

                btn_delete_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count > 0){
                            if(count == 1) {

                                contactView.setVisibility(View.VISIBLE);
                                arraySizeContactAdapter = mAdapter.returnMyObject().size();


                                RepKgAdapter mAdapter_two = new RepKgAdapter(getActivity(), 1, dialog, RepKgModel);
                                save.remove(1);
                                save.set(0, mAdapter_two);

                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);

                                mAdapter_two.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                                count = 0;
                                counter = 0;

                                Log.d("adasize","" + save.size());
                            }

                            if(count == 2){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 2, dialog, RepKgModel);
                                save.remove(2);
                                save.set(1, mAdapter_three);

                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);

                                count = 1;
                                counter = 0;

                            }

                            if(count == 3){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 3, dialog, RepKgModel);
                                save.remove(3);
                                save.set(2, mAdapter_three);

                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);

                                count = 2;
                                counter = 0;

                            }

                            if(count == 4){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 4, dialog, RepKgModel);
                                save.remove(4);
                                save.set(3, mAdapter_three);


                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);

                                count = 3;
                                counter = 0;

                                Log.d("count4","" +save.size());

                            }






                        }
                    }
                });


                btn_add_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(count < 4 && !save.get(0).returnMyObject().isEmpty()){


                            count++;
                            int size = 0;

                            Log.d("countAdd","" + count);

                            if(count == 1) {

                                contactView.setVisibility(View.VISIBLE);
                                arraySizeContactAdapter = mAdapter.returnMyObject().size();
                                Log.d("sizeCase66", "two before ini" + arraySizeContactAdapter);
                                RepKgModel = new RepKgModel(save.get(0).returnMyObject().get(0), save.get(0).returnMyObject().get(1), null, null,null,null,null,null,null,null);
                                RepKgAdapter mAdapter_two = new RepKgAdapter(getActivity(), 2, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(1,mAdapter_two);
                                size = mAdapter_two.returnMyObject().size();
                                mAdapter_two.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                            }


                            if( count == 2 ){
                                Log.d("countsize9","2:" + save.get(1).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(1).returnMyObject().get(0), save.get(1).returnMyObject().get(1), save.get(1).returnMyObject().get(2), save.get(1).returnMyObject().get(3),null,null,null,null,null,null);
                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 3, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);
                                save.add(2, mAdapter_three);
                                mAdapter_three.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                            }

                            if (count == 3){
                                Log.d("countsize9","3:" + save.get(2).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(2).returnMyObject().get(0), save.get(2).returnMyObject().get(1), save.get(2).returnMyObject().get(2), save.get(2).returnMyObject().get(3), save.get(2).returnMyObject().get(4), save.get(2).returnMyObject().get(5),null,null,null,null);
                                RepKgAdapter mAdapter_twoo = new RepKgAdapter(getActivity(), 4, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_twoo);
                                save.add(3,mAdapter_twoo);

                                mAdapter_twoo.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                            }

                            if (count == 4){
                                Log.d("countsize9","4:" + save.get(3).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(3).returnMyObject().get(0), save.get(3).returnMyObject().get(1), save.get(3).returnMyObject().get(2), save.get(3).returnMyObject().get(3), save.get(3).returnMyObject().get(4), save.get(3).returnMyObject().get(5),save.get(3).returnMyObject().get(6), save.get(3).returnMyObject().get(7),null,null);
                                RepKgAdapter mAdapter_two = new RepKgAdapter(getActivity(), 5, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(4,mAdapter_two);
                                mAdapter_two.setPlayPauseClickListener(benchFragment.this::imageButtonOnClick2);
                            }


                        }
                        else{
                            Toast.makeText(getContext(), "Only 5 sets are allowed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });
        return vx;
    }

    private void deleteString() {
        this.adapterCallBackString = "l1_reps";
    }


    private void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        cardViewAdapter mAdapter = new cardViewAdapter(getActivity(), lstBook);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setPlayPauseClickListener(this::imageButtonOnClick);
    }

    private void updateCard(int z) {
        int countstart = z;
        for (int x = 0; x < z; x++) {
            lstBook.set(x, new greenCardModel(R.drawable.quadratgruen));
        }

    }

    public void imageButtonOnClick(View v, int position) {
        // TODO: Implement this
        Log.d("cardview","hello Sir");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialogExtend xx = new dialogExtend();
        View view = getLayoutInflater().inflate(R.layout.open_training_data, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    public void imageButtonOnClick2(View v, int position, String x) {
        // TODO: Implement this

        View g = v;

        Log.d("heydu","" + x);

        EditText hey = v.findViewById(R.id.layer_two_et_symbol_reps_single_item);


        this.adapterCallBackString = x;

        builder.setView(g);



    }


    private void createCard() {
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));

        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));
        lstBook.add(new greenCardModel(R.drawable.quadrat40));




        int arraySize = lstBook.size();


    }

    private void updateProgressBar(int z) {
        int x = z;
        String zy = String.valueOf(z) + "%";
        pgBar.setProgress(x);
        tv.setText(zy);
    }


}
