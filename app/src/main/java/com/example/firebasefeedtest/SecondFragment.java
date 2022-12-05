package com.example.firebasefeedtest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment  {
    Context mm;
    Button bla;

    public SecondFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vx = inflater.inflate(R.layout.activitycard, container, false);
        bla = vx.findViewById(R.id.btn_add_data);
        bla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hallo", Toast.LENGTH_SHORT).show();
            }
        });
        return vx;
    }


}
