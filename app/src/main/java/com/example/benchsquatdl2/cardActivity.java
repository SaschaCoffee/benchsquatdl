package com.example.benchsquatdl2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benchsquatdl2.Fragment.FragmentMainActivity;


public class cardActivity extends AppCompatActivity {

    private ImageView img;
    Button btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        // Recieve data
        Intent intent = getIntent();
        String starter = intent.getExtras().getString("Thumbnail");



        btn_back = findViewById(R.id.btn_back);



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cardActivity.this, FragmentMainActivity.class);

                //passing data to the book activity
                intent.putExtra("exercise", starter);

                // start the activity

                startActivity(intent);
            }
        });







    }
}
