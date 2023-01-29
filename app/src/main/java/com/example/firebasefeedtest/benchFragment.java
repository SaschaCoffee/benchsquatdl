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

        String keyTraining = referenceTraininglogPublic.push().getKey();


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
                                reps = save.get(0).returnMyObject().get(0);
                                kg = save.get(0).returnMyObject().get(1);
                                Log.d("testDa","" + reps + "" + kg);
                                modelBench bench = new modelBench(reps,kg);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench);
                                break;

                            case 2:
                                reps = save.get(0).returnMyObject().get(0);
                                kg = save.get(0).returnMyObject().get(1);
                                reps2 = save.get(1).returnMyObject().get(2);
                                kg2 = save.get(1).returnMyObject().get(3);
                                Log.d("testDa","" + reps2 + "" + kg2);
                                modelBench bench2 = new modelBench(reps,kg,reps2,kg2);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench2);
                                break;
                            case 3:
                                reps = save.get(0).returnMyObject().get(0);
                                kg = save.get(0).returnMyObject().get(1);
                                reps2 = save.get(1).returnMyObject().get(2);
                                kg2 = save.get(1).returnMyObject().get(3);
                                reps3 = save.get(2).returnMyObject().get(4);
                                kg3 = save.get(2).returnMyObject().get(5);
                                modelBench bench3 = new modelBench(reps,kg,reps2,kg2,reps3,kg3);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench3);
                                Log.d("testDa","" + reps3 + "" + kg3);
                                break;

                            case 4:
                                reps = save.get(0).returnMyObject().get(0);
                                kg = save.get(0).returnMyObject().get(1);
                                reps2 = save.get(1).returnMyObject().get(2);
                                kg2 = save.get(1).returnMyObject().get(3);
                                reps3 = save.get(2).returnMyObject().get(4);
                                kg3 = save.get(2).returnMyObject().get(5);
                                reps4 = save.get(3).returnMyObject().get(6);
                                kg4 = save.get(3).returnMyObject().get(7);
                                modelBench bench4 = new modelBench(reps,kg,reps2,kg2,reps3,kg3,reps4,kg4);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench4);
                                Log.d("testDa","" + reps4 + "" + kg4);
                                break;

                            case 5:
                                reps = save.get(0).returnMyObject().get(0);
                                kg = save.get(0).returnMyObject().get(1);
                                reps2 = save.get(1).returnMyObject().get(2);
                                kg2 = save.get(1).returnMyObject().get(3);
                                reps3 = save.get(2).returnMyObject().get(4);
                                kg3 = save.get(2).returnMyObject().get(5);
                                reps4 = save.get(3).returnMyObject().get(6);
                                kg4 = save.get(3).returnMyObject().get(7);
                                reps5 = save.get(4).returnMyObject().get(8);
                                kg5 = save.get(4).returnMyObject().get(9);
                                modelBench bench5 = new modelBench(reps,reps2,reps3, reps4, reps5,kg, kg2,kg3,kg4,kg5);
                                referenceTraininglogPublic.child(keyTraining).setValue(bench5);
                                Log.d("testDa","" + reps5 + "" + kg5);
                                break;
                        }
                    }
                });

                btn_add_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                    }
                });



                btn_delete_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count > 0 ){
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
                        if(count <= 5 && save.size() > 0){
                          count++;

                            Log.d("sizeCase23", "count" + count);
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
