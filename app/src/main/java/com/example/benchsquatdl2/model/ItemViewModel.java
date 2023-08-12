package com.example.benchsquatdl2.model;

import android.content.ClipData;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    String vall ="test";
    MutableLiveData<String> selectedItem = new MutableLiveData<String>();

    public void selectItem(String item) {
        if(item == null) {
            selectedItem.setValue(vall);

        }else{
            vall = item;
            selectedItem.setValue(vall);

        }
    }
    public LiveData<String> getSelectedItem() {
        return selectedItem;
    }
    MutableLiveData<String> selectAAAedItem;
    public MutableLiveData<String> getCurrentName() {

        if(vall.equals("bench")) {
             selectAAAedItem = new MutableLiveData<String>();

                 selectAAAedItem.setValue(vall);
                 String plus = selectedItem.getValue();


        }
        else{
            selectAAAedItem = new MutableLiveData<String>();
            selectAAAedItem.setValue("WUHUUU");

        }


        return selectAAAedItem;


    }

}


