package com.example.benchsquatdl2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class statisticActivity extends AppCompatActivity {
    private LineChart mChart;
    private FirebaseUser user;
    private DatabaseReference reference, referenceTest;
    ArrayList<Long> firebaseArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        String childcardBench = "Best3BenchKg";
        user = FirebaseAuth.getInstance().getCurrentUser();
        referenceTest = FirebaseDatabase.getInstance().getReference("statisticData");
        firebaseArray = new ArrayList<>();
        drawGraph(firebaseArray);

        String str = getIntent().getStringExtra("item");

        referenceTest.child(str).child(childcardBench).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    long xxx = dataSnapshot.getValue(long.class);
                    firebaseArray.add(xxx);
                }
                drawGraph(firebaseArray);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void drawGraph(ArrayList<Long> firebaseArray) {
        Toast.makeText(this, "hallo" + firebaseArray.size(), Toast.LENGTH_SHORT).show();
        mChart = ((LineChart) findViewById(R.id.linechart));
        mChart.setDragEnabled(true);
        mChart.getXAxis().setEnabled(false);
        ArrayList<Entry> yValues = new ArrayList<>();
        Integer counter = 1;

        if(firebaseArray.size() > 0) {
            mChart.clear();
            for(int i = (firebaseArray.size())-1; i >= 0; i--){
                yValues.add(new Entry(counter, firebaseArray.get(i)));
                counter++;
            }

        }else{
            yValues.add(new Entry(0, 0));
        }


        Toast.makeText(this, "arraysize" + yValues.size(), Toast.LENGTH_SHORT).show();
        LineDataSet set1 = new LineDataSet(yValues, "BENCHPRESS");
        set1.setFillAlpha(110);
        set1.setCircleColor(Color.RED);
        set1.setLineWidth(8f);
        set1.setValueTextSize(10f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        mChart.setData(data);


    }


}








