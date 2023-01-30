package com.example.firebasefeedtest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class benchFragment extends Fragment  {
    Context mcontext;

    ArrayList<greenCardModel> lstBook = new ArrayList<>();
    private FirebaseUser user;
    private DatabaseReference reference,  referenceTest, referenceTraininglogPrivate, referenceTraininglogPublic,
            referenceCollectTrainingData, referenceTrainingLocation;
    ArrayList<dialogRepKgModel> dialogArrayReference = new ArrayList<>();
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
    dialogRepKgModel dialogRepKgModel;
    int arraySizeContactAdapter;
    String firstrep,firstset;
    ArrayList<ContactAdapter> save = new ArrayList<>();



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

        bre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sizeCase23", "countnull" + save.size());

                //OPEN DIALOG
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                dialogExtend xx = new dialogExtend();
                builder.setTitle("Enter Data");


                //FIRST RUN, COUNT == 1, ARRAY SIZE IN CONTACTADAPTER NULL
                View vieww = getLayoutInflater().inflate(R.layout.custom_dialog_list_recyclerview, null);
                RecyclerView contactView = vieww.findViewById(R.id.rv_customdialog);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                contactView.setLayoutManager(linearLayoutManager);
                contactView.setHasFixedSize(true);
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
                ContactAdapter mAdapter = new ContactAdapter(getActivity(), 1, dialog, dialogRepKgModel);
                save.add(0,mAdapter);
                contactView.setAdapter(save.get(0));

                String keyTraining = referenceTraininglogPublic.push().getKey();

                btn_upload_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("btn_add", "es geht");
                        Toast.makeText(getActivity(), "ggg", Toast.LENGTH_SHORT).show();
                        int size = save.size();
                        Log.d("arraySize", "hh" + size);
                        String reps, kg, reps2, kg2, reps3, kg3, reps4, kg4, reps5, kg5;


                        switch(size){
                            case 1:
                                EditText first_kg = vieww.findViewById(R.id.layer_one_kg);
                                EditText first_rep = vieww.findViewById(R.id.layer_one_reps);

                                String first_kg_string = first_kg.getText().toString().trim();
                                String first_rep_string = first_rep.getText().toString().trim();

                                modelBench bench = new modelBench(first_rep_string,first_kg_string);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench);
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

                                modelBench bench2 = new modelBench(first_rep_l2,second_rep_l2,first_kg_l2,second_kg_l2);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench2);
                                break;
                            case 3:
                                EditText first_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item);
                                EditText second_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_two);
                                EditText third_kg_layer3 = vieww.findViewById(R.id.layer_three_et_kg_card_single_item_three);

                                EditText first_rep_l3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item);
                                EditText second_rep_l3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two);
                                EditText third_rep_l3 = vieww.findViewById(R.id.layer_three_et_symbol_reps_single_item_two_three);

                                modelBench bench3 = new modelBench(first_rep_l3.getText().toString().trim(),second_rep_l3.getText().toString().trim(),third_rep_l3.getText().toString().trim(),first_kg_layer3.getText().toString().trim(), second_kg_layer3.getText().toString().trim(), third_kg_layer3.getText().toString());
                                referenceTraininglogPublic.child(keyTraining).setValue(bench3);
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



                                modelBench bench4 = new modelBench(rp1_l4, rp2_l4, rp3_l4, rp4_l4, kg1_l4, kg2_l4,kg3_l4,kg4_l4);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench4);
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

                                modelBench bench5 = new modelBench(rp1_l5,rp2_l5,rp3_l5,rp4_l5,rp5_l5,kg1_l5,kg2_l5,kg3_l5,kg4_l5, kg5_l5);

                                referenceTraininglogPublic.child(keyTraining).setValue(bench5);
                                break;
                        }
                    }
                });

                btn_delete_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count > 0){
                            count = 0;

                            save.remove(0);
                            contactView.setVisibility(View.VISIBLE);
                            ContactAdapter mAdapter = new ContactAdapter(getActivity(), 1, dialog, dialogRepKgModel);
                            save.add(0,mAdapter);
                            contactView.setAdapter(save.get(0));
                            dialog.create();

                            int size = save.size();
                            Log.d("arraySize", "hh" + size);


                        }
                    }
                });


                btn_add_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(count <= 5 && !save.get(0).returnMyObject().isEmpty()){
                          count++;

                            Log.d("sizeCase23", "count" + save.size());
                            int size = 0;

                            if(count == 1) {
                                Log.d("countsize9","1:" + save.get(0).returnMyObject().size());
                                contactView.setVisibility(View.VISIBLE);
                                arraySizeContactAdapter = mAdapter.returnMyObject().size();
                                Log.d("sizeCase66", "two before ini" + arraySizeContactAdapter);
                                dialogRepKgModel = new dialogRepKgModel(save.get(0).returnMyObject().get(0), save.get(0).returnMyObject().get(1), save.get(0).returnMyObject().get(0), save.get(0).returnMyObject().get(1),null,null,null,null,null,null);
                                ContactAdapter mAdapter_two = new ContactAdapter(getActivity(), 2, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(1,mAdapter_two);
                                size = mAdapter_two.returnMyObject().size();
                            }


                            if( count == 2 ){
                                Log.d("countsize9","2:" + save.get(1).returnMyObject().size());
                                dialogRepKgModel = new dialogRepKgModel(save.get(1).returnMyObject().get(0), save.get(1).returnMyObject().get(1), save.get(1).returnMyObject().get(2), save.get(1).returnMyObject().get(3),null,null,null,null,null,null);
                                ContactAdapter mAdapter_three = new ContactAdapter(getActivity(), 3, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);
                                save.add(2, mAdapter_three);
                            }

                            if (count == 3){
                                Log.d("countsize9","3:" + save.get(2).returnMyObject().size());
                                dialogRepKgModel = new dialogRepKgModel(save.get(2).returnMyObject().get(0), save.get(2).returnMyObject().get(1), save.get(2).returnMyObject().get(2), save.get(2).returnMyObject().get(3), save.get(2).returnMyObject().get(4), save.get(2).returnMyObject().get(5),null,null,null,null);
                                ContactAdapter mAdapter_two = new ContactAdapter(getActivity(), 4, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(3,mAdapter_two);
                            }

                            if (count == 4){
                                Log.d("countsize9","4:" + save.get(3).returnMyObject().size());
                                dialogRepKgModel = new dialogRepKgModel(save.get(3).returnMyObject().get(0), save.get(3).returnMyObject().get(1), save.get(3).returnMyObject().get(2), save.get(3).returnMyObject().get(3), save.get(3).returnMyObject().get(4), save.get(3).returnMyObject().get(5),save.get(3).returnMyObject().get(6), save.get(3).returnMyObject().get(7),null,null);
                                ContactAdapter mAdapter_two = new ContactAdapter(getActivity(), 5, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(4,mAdapter_two);
                            }
                        }
                        else{

                            Log.d("testDich", "it does not work bro XDDDDD");




                        }
                    }

                });

            }
        });




        return vx;
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
