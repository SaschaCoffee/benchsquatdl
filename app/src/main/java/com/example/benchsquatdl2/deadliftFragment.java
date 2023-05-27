package com.example.benchsquatdl2;

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
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class deadliftFragment extends Fragment implements
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
    int progr = 0;
    private Button btn_add_data,btn_delete_yes,btn_delete_item, upload_fb, btn_upload_data, btn_addtoTv, btntoTv, bre, btn_delete_data;
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
    int counterNew = 0;
    int countMe = 0;
    int progrrr = 0;



    public deadliftFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View vx = inflater.inflate(R.layout.activitycard_deadlift, container, false);
        pgBar = vx.findViewById(R.id.progressBar2);
        tv = vx.findViewById(R.id.text_view_progress_deadlift);


        referenceTraininglogPublic = FirebaseDatabase.getInstance().getReference("TraininglogPublic");

        mRecyclerView = vx.findViewById(R.id.recyclerview_deadlift);
        String childcard = "anzahlDeadlift";
        String childcardBench = "Best3DeadliftKg";
        String childcardDate = "Date";
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");
        referenceCollectTrainingData = FirebaseDatabase.getInstance().getReference("FeedTrainingData");
        referenceTraininglogPrivate = FirebaseDatabase.getInstance().getReference("TraininglogPrivateDeadlift");


        referenceTraininglogPublic = FirebaseDatabase.getInstance().getReference("TraininglogPublic");
        referenceTest = FirebaseDatabase.getInstance().getReference("teste");
        referenceTrainingLocation = FirebaseDatabase.getInstance().getReference("Location");
        // userid = user.getUid();

        createCard();
        buildRecyclerView();

        bre = vx.findViewById(R.id.btn_add_data_card_deadlift);

        btn_delete_item = vx.findViewById(R.id.btn_delete_item_deadlift);

        btn_delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(getActivity());
                View vieww = getLayoutInflater().inflate(R.layout.delete_areyousure, null);
                btn_delete_yes = vieww.findViewById(R.id.btn_delete_yes);
                builder.setView(vieww);

                dialog = builder.create();
                dialog.show();
                dialog.create();

                btn_delete_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        deleteLastItem();
                        dialog.cancel();
                        buildRecyclerView();

                    }
                });



            }
        });



        reference.child(user.getUid()).child(childcard).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    long model = snapshot.getValue(long.class);
                    progr = Integer.parseInt(String.valueOf(model));
                    Log.d("cardMe","value:" + progr);
                    updateCard(progr);
                    updateProgressBar(progr);
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

                for (int i = count;i>0;i--){
                    count--;
                    Log.d("countme4","" + count);
                }

                counter = 1;
                counterNew = 1;

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



                        if(keyEvent.getAction() == KeyEvent.ACTION_UP && !adapterCallBackString.isEmpty() ){
                            switch (code) {
                                case KeyEvent.KEYCODE_VOLUME_UP:

                                    tempCounter = counterNew;

                                    switch(adapterCallBackString){
                                        case "l1_reps":
                                            int text_rep;
                                            if(l1_reps.getText().toString().isEmpty() || l1_reps.getText().toString().trim().equals("0")){
                                                text_rep = 0;
                                            }
                                            else{
                                                text_rep  = Integer.parseInt(l1_reps.getText().toString().trim());
                                            }
                                            l1_reps.setText(String.valueOf(3+(text_rep)));
                                            break;
                                        case "l1_kg":

                                            int text_kg;
                                            if(l1_kg.getText().toString().isEmpty() || l1_kg.getText().toString().trim().equals("0")){
                                                text_kg = 0;
                                            }
                                            else{
                                                text_kg  = Integer.parseInt(l1_kg.getText().toString().trim());
                                            }

                                            l1_kg.setText(String.valueOf(20+(text_kg)));

                                            break;
                                        case "l2_reps_one":
                                            int txt_rep_l2 = Integer.parseInt(l2_reps_one.getText().toString().trim());
                                            Log.d("txt","mem" + txt_rep_l2);
                                            l2_reps_one.setText(String.valueOf(3 + txt_rep_l2));
                                            break;
                                        case "l2_reps_two":
                                            int txt_rep2_l2 = Integer.parseInt(l2_reps_two.getText().toString().trim());
                                            l2_reps_two.setText(String.valueOf(3 + txt_rep2_l2));
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
                                            l3_reps.setText(String.valueOf(3 + txt_rep_l3));
                                            break;
                                        case "l3_reps_two":
                                            int txt_rep2_l3 = Integer.parseInt(l3_reps_two.getText().toString().trim());
                                            l3_reps_two.setText(String.valueOf(txt_rep2_l3 + 3));
                                            break;
                                        case "l3_reps_three":
                                            int txt_rep3_l3 = Integer.parseInt(l3_reps_three.getText().toString().trim());
                                            l3_reps_three.setText(String.valueOf(3 + txt_rep3_l3));
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
                                            l4_reps.setText(String.valueOf(3 + txt_rep_l4));
                                            break;
                                        case "l4_reps_two":
                                            int txt_rep2_l4 = Integer.parseInt(l4_reps_two.getText().toString().trim());
                                            l4_reps_two.setText(String.valueOf(txt_rep2_l4 + 3));
                                            break;
                                        case "l4_reps_three":
                                            int txt_rep3_l4 = Integer.parseInt(l4_reps_three.getText().toString().trim());
                                            l4_reps_three.setText(String.valueOf(txt_rep3_l4 + 3));
                                            break;
                                        case "l4_reps_four":
                                            int txt_rep4_l4 = Integer.parseInt(l4_reps_four.getText().toString().trim());
                                            l4_reps_four.setText(String.valueOf(txt_rep4_l4 + 3));
                                            break;

                                        case "l5_reps":
                                            int txt_rep_l5 = Integer.parseInt(l5_reps.getText().toString().trim());
                                            l5_reps.setText(String.valueOf(3 + txt_rep_l5));
                                            break;
                                        case "l5_reps_two":
                                            int txt_rep2_l5 = Integer.parseInt(l5_reps_two.getText().toString().trim());
                                            l5_reps_two.setText(String.valueOf(3 + txt_rep2_l5));
                                            break;
                                        case "l5_reps_three":
                                            int txt_rep3_l5 = Integer.parseInt(l5_reps_three.getText().toString().trim());
                                            l5_reps_three.setText(String.valueOf(3 + txt_rep3_l5));
                                            break;
                                        case "l5_reps_four":
                                            int txt_rep4_l5 = Integer.parseInt(l5_reps_four.getText().toString().trim());
                                            l5_reps_four.setText(String.valueOf(3 + txt_rep4_l5));
                                            break;
                                        case "l5_reps_five":
                                            int txt_rep5_l5 = Integer.parseInt(l5_reps_five.getText().toString().trim());
                                            l5_reps_five.setText(String.valueOf(3 + txt_rep5_l5));
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


                        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && !adapterCallBackString.isEmpty()) {
                            switch (code) {

                                case KeyEvent.KEYCODE_VOLUME_DOWN:
                                    double counterNewDown = tempCounter - 1.25;
                                    tempCounter = counterNewDown;

                                    switch (adapterCallBackString) {
                                        case "l1_reps":
                                            int l1_reps_down;

                                            if(l1_reps.getText().toString().isEmpty() || l1_reps.getText().toString().trim().equals("0")){
                                                l1_reps_down = 1;
                                            }
                                            else{
                                                l1_reps_down = Integer.parseInt(l1_reps.getText().toString());
                                            }

                                            l1_reps.setText(String.valueOf(l1_reps_down - 1));
                                            break;
                                        case "l1_kg":
                                            int l1_kg_down;

                                            if(l1_kg.getText().toString().isEmpty() || l1_kg.getText().toString().trim().equals("0")){
                                                l1_kg_down = 2;
                                            }else{
                                                l1_kg_down = Integer.parseInt(l1_kg.getText().toString());
                                            }

                                            l1_kg.setText(String.valueOf(l1_kg_down - 2));
                                            break;

                                        case "l2_reps_two":
                                            int txt_rep2_l2;

                                            if(l2_reps_two.getText().toString().trim().equals("0") || l2_reps_two.getText().toString().isEmpty()){
                                                txt_rep2_l2 = 1;
                                            }
                                            else {
                                                boolean test = l2_reps_two.getText().equals("0");
                                                Log.d("layerTwo","TestAufruf" + test);
                                                txt_rep2_l2 = Integer.parseInt(l2_reps_two.getText().toString().trim());
                                            }
                                            l2_reps_two.setText(String.valueOf(txt_rep2_l2 -1));
                                            break;

                                        case "l2_kg_two":
                                            int txt_kg_l2;

                                            if(l2_kg_two.getText().toString().trim().equals("0") || l2_kg_two.getText().toString().isEmpty()){
                                                txt_kg_l2 = 2;
                                            }
                                            else {
                                                txt_kg_l2 = Integer.parseInt(l2_kg_two.getText().toString().trim());
                                            }

                                            l2_kg_two.setText(String.valueOf(txt_kg_l2 - 2));
                                            break;


                                        case "l3_kg_one":

                                            int txt_kg_l3;

                                            if(l2_kg_two.getText().toString().trim().equals("0") || l2_kg_two.getText().toString().isEmpty()){
                                                txt_kg_l3 = 2;
                                            }
                                            else {
                                                txt_kg_l3 = Integer.parseInt(l3_kg_one.getText().toString().trim());
                                            }

                                            l3_kg_one.setText(String.valueOf(txt_kg_l3 - 2));

                                            break;

                                        case "l3_kg_two":

                                            int txt_kg2_l3;

                                            if(l3_kg_two.getText().toString().trim().equals("0") || l3_kg_two.getText().toString().isEmpty()){
                                                txt_kg2_l3 = 2;
                                            }
                                            else {
                                                txt_kg2_l3 = Integer.parseInt(l3_kg_two.getText().toString().trim());
                                            }

                                            l3_kg_two.setText(String.valueOf(txt_kg2_l3 - 2));

                                            break;
                                        case "l3_kg_three":

                                            int txt_kg3_l3;

                                            if(l3_kg_three.getText().toString().trim().equals("0") || l3_kg_three.getText().toString().isEmpty()){
                                                txt_kg3_l3 = 2;
                                            }
                                            else {
                                                txt_kg3_l3 = Integer.parseInt(l3_kg_three.getText().toString().trim());
                                            }

                                            l3_kg_three.setText(String.valueOf(txt_kg3_l3 - 2));

                                            break;

                                        case "l3_reps":
                                            int txt_rep_l3;

                                            if(l3_reps.getText().toString().trim().equals("0") || l3_reps.getText().toString().isEmpty()){
                                                txt_rep_l3 = 1;
                                            }
                                            else {
                                                txt_rep_l3 = Integer.parseInt(l3_reps.getText().toString().trim());
                                            }

                                            l3_reps.setText(String.valueOf(txt_rep_l3 - 1));
                                            break;
                                        case "l3_reps_two":

                                            int txt_rep2_l3;

                                            if(l3_reps_two.getText().toString().trim().equals("0") || l3_reps_two.getText().toString().isEmpty()){
                                                txt_rep2_l3 = 1;
                                            }
                                            else {
                                                txt_rep2_l3 = Integer.parseInt(l3_reps_two.getText().toString().trim());
                                            }

                                            l3_reps_two.setText(String.valueOf(txt_rep2_l3 - 1));

                                            break;
                                        case "l3_reps_three":

                                            int txt_rep3_l3;

                                            if(l3_reps_three.getText().toString().trim().equals("0") || l3_reps_three.getText().toString().isEmpty()){
                                                txt_rep3_l3 = 1;
                                            }
                                            else {
                                                txt_rep3_l3 = Integer.parseInt(l3_reps_three.getText().toString().trim());
                                            }

                                            l3_reps_three.setText(String.valueOf(txt_rep3_l3 - 1));
                                            break;

                                        case "l4_kg_one":
                                            int txt_kg_l4;

                                            if(l4_kg_one.getText().toString().trim().equals("0") || l4_kg_one.getText().toString().isEmpty()){
                                                txt_kg_l4 = 2;
                                            }
                                            else {
                                                txt_kg_l4 = Integer.parseInt(l4_kg_one.getText().toString().trim());
                                            }

                                            l4_kg_one.setText(String.valueOf(txt_kg_l4 - 2));
                                            break;
                                        case "l4_kg_two":
                                            int txt_kg2_l4;

                                            if(l4_kg_two.getText().toString().trim().equals("0") || l4_kg_two.getText().toString().isEmpty()){
                                                txt_kg2_l4 = 2;
                                            }
                                            else {
                                                txt_kg2_l4 = Integer.parseInt(l4_kg_two.getText().toString().trim());
                                            }

                                            l4_kg_two.setText(String.valueOf(txt_kg2_l4 - 2));

                                            break;
                                        case "l4_kg_three":

                                            int txt_kg3_l4;

                                            if(l4_kg_three.getText().toString().trim().equals("0") || l4_kg_three.getText().toString().isEmpty()){
                                                txt_kg3_l4 = 2;
                                            }
                                            else {
                                                txt_kg3_l4 = Integer.parseInt(l4_kg_three.getText().toString().trim());
                                            }

                                            l4_kg_three.setText(String.valueOf(txt_kg3_l4 - 2));

                                            break;
                                        case "l4_kg_four":

                                            int txt_kg4_l4;

                                            if(l4_kg_four.getText().toString().trim().equals("0") || l4_kg_four.getText().toString().isEmpty()){
                                                txt_kg4_l4 = 2;
                                            }
                                            else {
                                                txt_kg4_l4 = Integer.parseInt(l4_kg_four.getText().toString().trim());
                                            }

                                            l4_kg_four.setText(String.valueOf(txt_kg4_l4 - 2));
                                            break;

                                        case "l4_reps":
                                            int txt_reps_l4;

                                            if(l4_reps.getText().toString().trim().equals("0") || l4_reps.getText().toString().isEmpty()){
                                                txt_reps_l4 = 1;
                                            }
                                            else {
                                                txt_reps_l4 = Integer.parseInt(l4_reps.getText().toString().trim());
                                            }

                                            l4_reps.setText(String.valueOf(txt_reps_l4 - 1));

                                            break;
                                        case "l4_reps_two":

                                            int txt_reps2_l4;

                                            if(l4_reps_two.getText().toString().trim().equals("0") || l4_reps_two.getText().toString().isEmpty()){
                                                txt_reps2_l4 = 1;
                                            }
                                            else {
                                                txt_reps2_l4 = Integer.parseInt(l4_reps_two.getText().toString().trim());
                                            }

                                            l4_reps.setText(String.valueOf(txt_reps2_l4 - 1));

                                            l4_reps_two.setText(String.valueOf(txt_reps2_l4 - 1));
                                            break;
                                        case "l4_reps_three":
                                            int txt_reps3_l4;

                                            if(l4_reps_three.getText().toString().trim().equals("0") || l4_reps_three.getText().toString().isEmpty()){
                                                txt_reps3_l4 = 1;
                                            }
                                            else {
                                                txt_reps3_l4 = Integer.parseInt(l4_reps_three.getText().toString().trim());
                                            }
                                            l4_reps_three.setText(String.valueOf(txt_reps3_l4 - 1));
                                            break;
                                        case "l4_reps_four":
                                            int txt_reps4_l4;

                                            if(l4_reps_four.getText().toString().trim().equals("0") || l4_reps_four.getText().toString().isEmpty()){
                                                txt_reps4_l4 = 1;
                                            }
                                            else {
                                                txt_reps4_l4 = Integer.parseInt(l4_reps_four.getText().toString().trim());
                                            }

                                            l4_reps_four.setText(String.valueOf(txt_reps4_l4 - 1));

                                            break;

                                        case "l5_reps":
                                            int txt_reps_l5;

                                            if(l5_reps.getText().toString().trim().equals("0") || l5_reps.getText().toString().isEmpty()){
                                                txt_reps_l5 = 1;
                                            }
                                            else {
                                                txt_reps_l5 = Integer.parseInt(l5_reps.getText().toString().trim());
                                            }

                                            l5_reps.setText(String.valueOf(txt_reps_l5 - 1));

                                            break;
                                        case "l5_reps_two":
                                            int txt_rep2_l5;

                                            if(l5_reps_two.getText().toString().trim().equals("0") || l5_reps_two.getText().toString().isEmpty()){
                                                txt_rep2_l5 = 1;
                                            }
                                            else {
                                                txt_rep2_l5 = Integer.parseInt(l5_reps_two.getText().toString().trim());
                                            }

                                            l5_reps_two.setText(String.valueOf(txt_rep2_l5 - 1));

                                            break;
                                        case "l5_reps_three":
                                            int txt_rep3_l5;

                                            if(l5_reps_three.getText().toString().trim().equals("0") || l5_reps_three.getText().toString().isEmpty()){
                                                txt_rep3_l5 = 1;
                                            }
                                            else {
                                                txt_rep3_l5 = Integer.parseInt(l5_reps_three.getText().toString().trim());
                                            }

                                            l5_reps_three.setText(String.valueOf(txt_rep3_l5 - 1));
                                            break;

                                        case "l5_reps_four":
                                            int txt_rep4_l5;

                                            if(l5_reps_four.getText().toString().trim().equals("0") || l5_reps_four.getText().toString().isEmpty()){
                                                txt_rep4_l5 = 1;
                                            }
                                            else {
                                                txt_rep4_l5 = Integer.parseInt(l5_reps_four.getText().toString().trim());
                                            }
                                            l5_reps_four.setText(String.valueOf(txt_rep4_l5 - 1));
                                            break;
                                        case "l5_reps_five":
                                            int txt_rep5_l5;

                                            if(l5_reps_five.getText().toString().trim().equals("0") || l5_reps_five.getText().toString().isEmpty()){
                                                txt_rep5_l5 = 1;
                                            }
                                            else {
                                                txt_rep5_l5 = Integer.parseInt(l5_reps_five.getText().toString().trim());
                                            }

                                            l5_reps_five.setText(String.valueOf(txt_rep5_l5 - 1));
                                            break;

                                        case "l5_kg_one":
                                            int txt_kg_l5;

                                            if(l5_kg_one.getText().toString().trim().equals("0") || l5_kg_one.getText().toString().isEmpty()){
                                                txt_kg_l5 = 2;
                                            }
                                            else {
                                                txt_kg_l5 = Integer.parseInt(l5_kg_one.getText().toString().trim());
                                            }

                                            l5_kg_one.setText(String.valueOf(txt_kg_l5 - 2));

                                            break;
                                        case "l5_kg_two":
                                            int txt_kg2_l5;

                                            if(l5_kg_two.getText().toString().trim().equals("0") || l5_kg_two.getText().toString().isEmpty()){
                                                txt_kg2_l5 = 2;
                                            }
                                            else {
                                                txt_kg2_l5 = Integer.parseInt(l5_kg_two.getText().toString().trim());
                                            }

                                            l5_kg_two.setText(String.valueOf(txt_kg2_l5 - 2));
                                            break;

                                        case "l5_kg_three":
                                            int txt_kg3_l5;

                                            if(l5_kg_three.getText().toString().trim().equals("0") || l5_kg_three.getText().toString().isEmpty()){
                                                txt_kg3_l5 = 2;
                                            }
                                            else {
                                                txt_kg3_l5 = Integer.parseInt(l5_kg_three.getText().toString().trim());
                                            }

                                            l5_kg_three.setText(String.valueOf(txt_kg3_l5 - 2));

                                            break;
                                        case "l5_kg_four":
                                            int txt_kg4_l5;

                                            if(l5_kg_four.getText().toString().trim().equals("0") || l5_kg_four.getText().toString().isEmpty()){
                                                txt_kg4_l5 = 2;
                                            }
                                            else {
                                                txt_kg4_l5 = Integer.parseInt(l5_kg_four.getText().toString().trim());
                                            }

                                            l5_kg_four.setText(String.valueOf(txt_kg4_l5 - 2));

                                            break;
                                        case "l5_kg_five":

                                            int txt_kg5_l5;

                                            if(l5_kg_five.getText().toString().trim().equals("0") || l5_kg_five.getText().toString().isEmpty()){
                                                txt_kg5_l5 = 2;
                                            }
                                            else {
                                                txt_kg5_l5 = Integer.parseInt(l5_kg_five.getText().toString().trim());
                                            }

                                            l5_kg_five.setText(String.valueOf(txt_kg5_l5 - 2));

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

                //IMPORT FUNCTION. WITHOUT IT -> Save will jump to 2,3,4 (SIZE)
                boolean x = dialog.isShowing();
                if(x){
                    if(save.size() > 0) {
                        save.remove(0);
                    }
                }
                save.add(0,mAdapter);
                contactView.setAdapter(save.get(0));
                mAdapter.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                String keyTraining = referenceTraininglogPublic.push().getKey();
                Log.d("saveAddSize","" + save.size());




                btn_upload_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DateFormat df = new SimpleDateFormat("d MMM yyyy");
                        String date = df.format(Calendar.getInstance().getTime());
                        String dummyTxt = "Add a comment";

                        switch(save.size()){

                            case 1:
                                EditText first_kg = vieww.findViewById(R.id.layer_one_kg);
                                EditText first_rep = vieww.findViewById(R.id.layer_one_reps);

                                String first_kg_string = first_kg.getText().toString().trim();
                                String first_rep_string = first_rep.getText().toString().trim();

                                if(!first_kg_string.isEmpty() && !first_rep_string.isEmpty()) {
                                    modelDeadlift deadlift = new modelDeadlift(first_rep_string, first_kg_string, date, dummyTxt);
                                    referenceTraininglogPublic.child(keyTraining).setValue(deadlift);


                                    progr++;

                                    reference.child(user.getUid()).child(childcard).setValue(progr);
                                    referenceTraininglogPrivate.child(user.getUid()).child(String.valueOf(progr)).setValue(deadlift);

                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();

                                    dialog.cancel();
                                    //RESET
                                    count = 0;

                                    counter = 0;
                                    counterNew = 0;

                                    save.remove(0);
                                    adapterCallBackString = "l1_reps";

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

                                    modelDeadlift deadlift2 = new modelDeadlift(first_rep_l2, second_rep_l2, first_kg_l2, second_kg_l2, date, dummyTxt);
                                    progr++;

                                    reference.child(user.getUid()).child(childcard).setValue(progr);
                                    referenceTraininglogPublic.child(keyTraining).setValue(deadlift2);
                                    referenceTraininglogPrivate.child(user.getUid()).child(String.valueOf(progr)).setValue(deadlift2);


                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();

                                    dialog.cancel();
                                    //RESET
                                    count = 0;
                                    save.clear();


                                    adapterCallBackString = "l1_reps";
                                    counterNew = 0;
                                    counter = 0;



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
                                    modelDeadlift deadlift3 = new modelDeadlift(first_rep_l3, second_rep_l3, third_rep_l3, first_kg_l3, second_kg_l3, third_kg_l3, date, dummyTxt);

                                    progr++;

                                    reference.child(user.getUid()).child(childcard).setValue(progr);
                                    referenceTraininglogPublic.child(keyTraining).setValue(deadlift3);
                                    referenceTraininglogPrivate.child(user.getUid()).child(String.valueOf(progr)).setValue(deadlift3);



                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();
                                    counterNew = 0;

                                    dialog.cancel();

                                    counter = 0;

                                    //RESET
                                    count = 0;
                                    save.clear();

                                    adapterCallBackString = "l1_reps";

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

                                    modelDeadlift deadlift4 = new modelDeadlift(rp1_l4, rp2_l4, rp3_l4, rp4_l4, kg1_l4, kg2_l4, kg3_l4, kg4_l4, date, dummyTxt);

                                    progr++;
                                    reference.child(user.getUid()).child(childcard).setValue(progr);
                                    referenceTraininglogPublic.child(keyTraining).setValue(deadlift4);
                                    referenceTraininglogPrivate.child(user.getUid()).child(String.valueOf(progr)).setValue(deadlift4);



                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();

                                    counter = 0;

                                    dialog.cancel();
                                    //RESET
                                    count = 0;
                                    save.clear();

                                    adapterCallBackString = "l1_reps";
                                    counterNew = 0;

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

                                    modelDeadlift deadlift5 = new modelDeadlift(rp1_l5, rp2_l5, rp3_l5, rp4_l5, rp5_l5, kg1_l5, kg2_l5, kg3_l5, kg4_l5, kg5_l5, date, dummyTxt);


                                    progr++;
                                    reference.child(user.getUid()).child(childcard).setValue(progr);
                                    referenceTraininglogPublic.child(keyTraining).setValue(deadlift5);
                                    referenceTraininglogPrivate.child(user.getUid()).child(String.valueOf(progr)).setValue(deadlift5);

                                    updateProgressBar(progr);
                                    updateCard(progr);
                                    buildRecyclerView();

                                    counter = 0;

                                    dialog.cancel();
                                    //RESET
                                    count = 0;
                                    adapterCallBackString = "l1_reps";
                                    counterNew = 0;
                                    save.clear();

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

                                mAdapter_two.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                                count = 0;
                                counter = 0;
                                adapterCallBackString = "l1_reps_one";

                                counterNew = 0;

                                Log.d("adasize","" + save.size());
                            }

                            if(count == 2){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 2, dialog, RepKgModel);
                                save.remove(2);
                                save.set(1, mAdapter_three);

                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);

                                count = 1;
                                counter = 0;
                                adapterCallBackString = "l2_reps_one";
                                counterNew = 0;

                            }

                            if(count == 3){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 3, dialog, RepKgModel);
                                save.remove(3);
                                save.set(2, mAdapter_three);

                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);

                                count = 2;
                                counter = 0;
                                adapterCallBackString = "l3_reps_one";
                                counterNew = 0;

                            }

                            if(count == 4){

                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 4, dialog, RepKgModel);
                                save.remove(4);
                                save.set(3, mAdapter_three);


                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);

                                mAdapter_three.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);

                                count = 3;
                                counter = 0;

                                Log.d("count4","" +save.size());
                                adapterCallBackString = "l4_reps_five";
                                counterNew = 0;

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
                                mAdapter_two.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                                adapterCallBackString = "l2_reps_two";
                            }


                            if( count == 2 ){
                                Log.d("countsize9","2:" + save.get(1).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(1).returnMyObject().get(0), save.get(1).returnMyObject().get(1), save.get(1).returnMyObject().get(2), save.get(1).returnMyObject().get(3),null,null,null,null,null,null);
                                RepKgAdapter mAdapter_three = new RepKgAdapter(getActivity(), 3, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);
                                save.add(2, mAdapter_three);
                                mAdapter_three.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                                adapterCallBackString = "l3_reps_three";
                            }

                            if (count == 3){
                                Log.d("countsize9","3:" + save.get(2).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(2).returnMyObject().get(0), save.get(2).returnMyObject().get(1), save.get(2).returnMyObject().get(2), save.get(2).returnMyObject().get(3), save.get(2).returnMyObject().get(4), save.get(2).returnMyObject().get(5),null,null,null,null);
                                RepKgAdapter mAdapter_twoo = new RepKgAdapter(getActivity(), 4, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_twoo);
                                save.add(3,mAdapter_twoo);

                                mAdapter_twoo.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                                adapterCallBackString = "l4_reps_four";
                            }

                            if (count == 4){
                                Log.d("countsize9","4:" + save.get(3).returnMyObject().size());
                                RepKgModel = new RepKgModel(save.get(3).returnMyObject().get(0), save.get(3).returnMyObject().get(1), save.get(3).returnMyObject().get(2), save.get(3).returnMyObject().get(3), save.get(3).returnMyObject().get(4), save.get(3).returnMyObject().get(5),save.get(3).returnMyObject().get(6), save.get(3).returnMyObject().get(7),null,null);
                                RepKgAdapter mAdapter_two = new RepKgAdapter(getActivity(), 5, dialog, RepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(4,mAdapter_two);
                                mAdapter_two.setPlayPauseClickListener(deadliftFragment.this::imageButtonOnClick2);
                                adapterCallBackString = "l5_reps_one";
                            }


                        }
                        else{
                            Toast.makeText(getContext(), "Fill out everything. Only 5 sets are allowed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }



        });
        return vx;
    }

    private String saveRep(String x) {
        return x;
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
        int x = 0;

        for (x = 0; x < 39; x++) {
            lstBook.set(x, new greenCardModel(R.drawable.quadrat40));
        }

        for (x = 0; x < z; x++) {
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

        TextView date = view.findViewById(R.id.tv_date);
        MultiAutoCompleteTextView cc = view.findViewById(R.id.multiAutoCompleteTextView);

        TextView rep1 = view.findViewById(R.id.tv_reps_opendialog);
        TextView rep2 = view.findViewById(R.id.tv_reps_opendialog2);
        TextView rep3 = view.findViewById(R.id.tv_reps_opendialog3);
        TextView rep4 = view.findViewById(R.id.tv_reps_opendialog4);
        TextView rep5 = view.findViewById(R.id.tv_reps_opendialog5);

// KG STARTS HERE, BUT I NAMED IT TO REPS FOR THE LOOP IN THE NEXT PARAGRAPH
        TextView rep6 = view.findViewById(R.id.tv_kg_opendialog);
        TextView rep7 = view.findViewById(R.id.tv_kg_opendialog2);
        TextView rep8 = view.findViewById(R.id.tv_kg_opendialog3);
        TextView rep9 = view.findViewById(R.id.tv_kg_opendialog4);
        TextView rep10 = view.findViewById(R.id.tv_kg_opendialog5);

        String xy = (String.valueOf(position+1));

        Button save = view.findViewById(R.id.button3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getComment = cc.getText().toString().trim();
                commentObject xx = new commentObject(getComment);

                referenceTraininglogPrivate.child(user.getUid()).child(xy).child("xNote").setValue(getComment);
                dialog.dismiss();

            }
        });




        Query bre = referenceTraininglogPrivate.child(user.getUid()).child(xy);


        bre.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("count4","" + snapshot.getChildrenCount());
                String count = String.valueOf(snapshot.getChildrenCount());

                switch(count){
                    case "4":
                        for(DataSnapshot pee : snapshot.getChildren()) {
                            progrrr++;
                            String counterString = "rep" + String.valueOf(progrrr);
                            Log.d("counterSA","" + counterString);
                            String x = pee.getValue(String.class);

                            Log.d("counterSS","" + x);

                            switch(counterString){
                                case "rep1":
                                    Log.d("counterS","peeeee");
                                    rep1.setText(x);
                                    break;

                                case "rep2":
                                    rep6.setText(x);
                                    break;
                                case "rep3":
                                    date.setText(x);
                                    break;
                                case "rep4":
                                    cc.setText(x);
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                            }
                        }
                        break;

                    case "6":
                        for(DataSnapshot pee : snapshot.getChildren()) {
                            progrrr++;
                            String counterString = "rep" + String.valueOf(progrrr);
                            Log.d("counterS","" + counterString);
                            String x = pee.getValue(String.class);

                            Log.d("counterSS","" + x);

                            switch(counterString){
                                case "rep1":
                                    Log.d("counterS","peeeee");
                                    rep1.setText(x);
                                    break;

                                case "rep2":
                                    //KG
                                    rep2.setText(x);
                                    break;

                                case "rep3":
                                    //REP
                                    rep6.setText(x);
                                    break;

                                case "rep4":
                                    //KG
                                    rep7.setText(x);
                                    break;
                                case "rep5":
                                    date.setText(x);
                                    break;
                                case "rep6":
                                    cc.setText(x);
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    break;

                            }
                        }break;
                    case "8":
                        for(DataSnapshot pee : snapshot.getChildren()) {
                            progrrr++;
                            String counterString = "rep" + String.valueOf(progrrr);
                            Log.d("counterS","" + counterString);
                            String x = pee.getValue(String.class);

                            Log.d("counterSS","" + x);

                            switch(counterString){
                                case "rep1":
                                    Log.d("counterS","peeeee");
                                    rep1.setText(x);
                                    break;

                                case "rep2":
                                    //KG
                                    rep2.setText(x);
                                    break;

                                case "rep3":
                                    //REP
                                    rep3.setText(x);
                                    break;

                                case "rep4":
                                    //KG
                                    rep6.setText(x);
                                    break;

                                case "rep5":
                                    rep7.setText(x);
                                    break;

                                case "rep6":
                                    rep8.setText(x);
                                    break;

                                case "rep7":
                                    date.setText(x);
                                    break;
                                case "rep8":
                                    cc.setText(x);
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    break;

                            }
                        }break;

                    case "10":
                        for(DataSnapshot pee : snapshot.getChildren()) {
                            progrrr++;
                            String counterString = "rep" + String.valueOf(progrrr);
                            Log.d("counterS","" + counterString);
                            String x = pee.getValue(String.class);

                            Log.d("counterSS","" + x);

                            switch(counterString){
                                case "rep1":
                                    Log.d("counterS","peeeee");
                                    rep1.setText(x);
                                    break;

                                case "rep2":
                                    //KG
                                    rep2.setText(x);
                                    break;

                                case "rep3":
                                    //REP
                                    rep3.setText(x);
                                    break;

                                case "rep4":
                                    //KG
                                    rep4.setText(x);
                                    break;

                                case "rep5":
                                    rep6.setText(x);
                                    break;

                                case "rep6":
                                    rep7.setText(x);
                                    break;

                                case "rep7":
                                    rep8.setText(x);
                                    break;

                                case "rep8":
                                    rep9.setText(x);
                                    break;

                                case "rep9":
                                    date.setText(x);
                                    break;

                                case "rep10":
                                    cc.setText(x);
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    break;

                            }
                        }break;

                    case "12" :
                        for(DataSnapshot pee : snapshot.getChildren()) {
                            progrrr++;
                            String counterString = "rep" + String.valueOf(progrrr);
                            Log.d("counterS","" + counterString);
                            String x = pee.getValue(String.class);

                            Log.d("counterSS","" + x);

                            switch(counterString){
                                case "rep1":
                                    Log.d("counterS","peeeee");
                                    rep1.setText(x);
                                    break;

                                case "rep2":
                                    //KG
                                    rep2.setText(x);
                                    break;

                                case "rep3":
                                    //REP
                                    rep3.setText(x);
                                    break;

                                case "rep4":
                                    //KG
                                    rep4.setText(x);
                                    break;

                                case "rep5":
                                    rep5.setText(x);
                                    break;

                                case "rep6":
                                    rep6.setText(x);
                                    break;

                                case "rep7":
                                    rep7.setText(x);
                                    break;

                                case "rep8":
                                    rep8.setText(x);
                                    break;

                                case "rep9":
                                    rep9.setText(x);
                                    break;
                                case "rep10":
                                    rep10.setText(x);
                                    break;

                                case "rep11":
                                    date.setText(x);
                                    break;
                                case "rep12":
                                    cc.setText(x);
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    progrrr--;
                                    break;

                            }
                        }break;




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        referenceTraininglogPrivate.child(user.getUid()).child("4").limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot pee : snapshot.getChildren()) {
                    String x = pee.getValue(String.class);
                    Log.d("GibMirEs", "" + x);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        referenceTraininglogPrivate.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.d("snak","" + snapshot.getChildren());

                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        referenceTraininglogPrivate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.d("snaps","AAA" + snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void deleteLastItem(){
        reference = FirebaseDatabase.getInstance().getReference("metaDateUser");
        referenceTraininglogPrivate = FirebaseDatabase.getInstance().getReference("TraininglogPrivateDeadlift");


        DatabaseReference quotesRef = referenceTraininglogPrivate.child(user.getUid());

        if (progr > 0){
            progr--;
        }


        quotesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String test = String.valueOf(snapshot.getChildrenCount());

                String x = String.valueOf(snapshot.getChildrenCount());
                int newC = Integer.parseInt(x);

                DatabaseReference quotesRef = referenceTraininglogPrivate.child(user.getUid()).child(x);
                quotesRef.removeValue();

                if(newC > 0) {
                    newC--;
                    reference.child(user.getUid()).child("anzahlDeadlift").setValue(newC);
                    updateCard(Integer.parseInt(test));
                    updateCard(newC);
                    buildRecyclerView();
                }
                else{
                    newC = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








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
