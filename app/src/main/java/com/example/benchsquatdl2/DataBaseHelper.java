package com.example.benchsquatdl2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_NAME = "column_name";
    public static final String COLUMN_INSTA = "column_insta";



    String GERMANY = "GERMANY";

    Context contextt;



    public DataBaseHelper(@Nullable Context context) {
        super(context, "neueDB.db", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String x = "CREATE TABLE GERMANY (" + COLUMN_NAME + ", " + COLUMN_INSTA + " )";
        db.execSQL(x);
    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + GERMANY);
        onCreate(db);

    }

    ArrayList<modelDisplayRv> listContacts() {
        String sql = "select * from " + GERMANY;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<modelDisplayRv> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String insta = cursor.getString(1);

                storeContacts.add(new modelDisplayRv(name, insta));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }


    void csvcopy(Context contex){
        SQLiteDatabase db = this.getWritableDatabase();
        String myCSVFile = "worldwideroh.csv";
        AssetManager manager = contex.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(myCSVFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        db.beginTransaction();
        try{
            while ((line = buffer.readLine()) != null){
                String[] columns = line.split(";");
                if(columns.length != 2) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                ContentValues cv = new ContentValues();

                cv.put(COLUMN_NAME, columns[0].trim());
                cv.put(COLUMN_INSTA, columns[1].trim());

                db.insert(GERMANY, null, cv);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    void addOne(ModelAdd modeladd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, modeladd.getName());


        db.insert(GERMANY, null,cv);

    }

    public ArrayList<modelDisplayRv> listContactsRv() {
        String sql = "select * from " + GERMANY;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<modelDisplayRv> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() && storeContacts.size() < 19) {
            Log.d("storeCont","");
            do {
                String name = cursor.getString(0);
                String insta = cursor.getString(1);

                storeContacts.add(new modelDisplayRv(name, insta));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }
}
