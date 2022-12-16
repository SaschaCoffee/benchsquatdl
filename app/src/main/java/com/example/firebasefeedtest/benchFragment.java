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
    Button xyz;

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
    private Button btn_add_data, btn_upload_data, btn_addtoTv, btntoTv, bre, btn_delete_data;
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
                btn_delete_data = vieww.findViewById(R.id.delete_btn_rv);
                dialog = builder.create();
                dialog.show();
                dialog.create();

                contactView.setVisibility(View.VISIBLE);
                ContactAdapter mAdapter = new ContactAdapter(getActivity(), 1, dialog, dialogRepKgModel);
                contactView.setAdapter(mAdapter);



                btn_delete_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count >0 ){
                            count--;
                            contactView.setVisibility(View.VISIBLE);
                            ContactAdapter mAdapter = new ContactAdapter(getActivity(), count, dialog, dialogRepKgModel);
                            contactView.setAdapter(mAdapter);
                        }
                    }
                });



                btn_add_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(count <= 5){

                          count++;

                            Log.d("sizeCase3", "count" + count);

                        if(count == 1 | count == 2){
                            int size = 0;


                            Log.d("sizeCase3", "count" + size);
                            if(count == 1) {
                                contactView.setVisibility(View.VISIBLE);
                                arraySizeContactAdapter = mAdapter.returnMyObject().size();
                                Log.d("sizeCase", "two before ini" + arraySizeContactAdapter);
                                dialogRepKgModel = new dialogRepKgModel(mAdapter.returnMyObject().get(0), mAdapter.returnMyObject().get(1), mAdapter.returnMyObject().get(0), mAdapter.returnMyObject().get(1),null,null,null,null,null,null);
                                ContactAdapter mAdapter_two = new ContactAdapter(getActivity(), 2, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(mAdapter_two);
                                size = mAdapter_two.returnMyObject().size();
                            }


                            if( count == 2 ){
                                dialogRepKgModel = new dialogRepKgModel(save.get(0).returnMyObject().get(0), save.get(0).returnMyObject().get(1), save.get(0).returnMyObject().get(2), save.get(0).returnMyObject().get(3),null,null,null,null,null,null);
                                ContactAdapter mAdapter_three = new ContactAdapter(getActivity(), 3, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_three);
                                save.add(mAdapter_three);
                            }

                            if (count == 3){
                                dialogRepKgModel = new dialogRepKgModel(save.get(0).returnMyObject().get(0), save.get(0).returnMyObject().get(1), save.get(0).returnMyObject().get(2), save.get(0).returnMyObject().get(3), save.get(0).returnMyObject().get(4), save.get(0).returnMyObject().get(5),null,null,null,null);
                                ContactAdapter mAdapter_two = new ContactAdapter(getActivity(), 3, dialog, dialogRepKgModel);
                                contactView.setVisibility(View.VISIBLE);
                                contactView.setAdapter(mAdapter_two);
                                save.add(mAdapter_two);
                            }





                        }







                        }


                    }
                });

            }
        });




        return vx;
    }












}
