package com.example.benchsquatdl2.Fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.benchsquatdl2.R;
import com.example.benchsquatdl2.startActivityForLoginRegister;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


public class ThirdFragment extends Fragment {
    ImageView imgProfile;
    Button bb;

    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vx = inflater.inflate(R.layout.fragment_third, container, false);
        imgProfile = vx.findViewById(R.id.thirdFragment_img);
        bb = vx.findViewById(R.id.btn_logout);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                startActivity((new Intent(getActivity(), startActivityForLoginRegister.class)));
            }
        });

        return vx;
    }
}
