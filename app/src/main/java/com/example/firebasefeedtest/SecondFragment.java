package com.example.firebasefeedtest;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SecondFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener  {
    private FirebaseUser user;
    private DatabaseReference reference, referenceTest, referenceTraininglogPrivate, referenceTraininglogPublic,
            referenceCollectTrainingData, referenceTrainingLocation;
    ArrayList<greenCardModel> lstBook = new ArrayList<>();
    ArrayList<Long> firebaseArray;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar pgBar;
    private TextView tv, heavy1, heavy2, heavy3;
    private EditText et_first_set, et_second_set, et_third_set, et_kg, et_kg2, et_kg3;
    int progr;
    private Button btn_add_data, btn_upload_data, btn_addtoTv, btntoTv;
    private AlertDialog dialog;
    private String userid;
    private int counter = 0;
    int arraycounter = 0;
    int sizeField = 0;
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
    View lala;
    Context mm;
    Button bla;
    Integer choosen = 1;
    MenuItem start;
    BottomNavigationView bottomNavigationView;

    public SecondFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("vaalla", "hallo1");
        View vx = inflater.inflate(R.layout.activitycard2, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialogExtend xx = new dialogExtend();
        builder.setTitle("Enter Data");

        bottomNavigationView = vx.findViewById(R.id.topNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.start_squad);


        //pgBar = vx.findViewById(R.id.progress_bar);
        //  tv = vx.findViewById(R.id.text_view_progress);


        // builder.setOnKeyListener(xxy);
        // createCard();
        // buildRecyclerView();
        // builder.setView(view);
        // dialog = builder.create();

        return vx;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("ddg", "bree" + item.getItemId());
        benchFragment benchFragment = new benchFragment();
        squatFragment squatFragment = new squatFragment();
        deadliftFragment deadliftFragment = new deadliftFragment();

        switch(item.getItemId()){
            case R.id.bench_card:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containertwo,benchFragment).commit();
                break;
            case R.id.deadlift_card:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containertwo,deadliftFragment).commit();
                break;
            case R.id.squad_card:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containertwo,squatFragment).commit();
        }

        return false;
    }




}
