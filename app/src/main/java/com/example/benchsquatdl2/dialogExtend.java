package com.example.benchsquatdl2;

import android.view.KeyEvent;
import android.view.View;

public class dialogExtend implements View.OnClickListener {

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_VOLUME_DOWN){
          return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
