package com.example.benchsquatdl2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.benchsquatdl2.model.ProfileStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataBaseStatistic extends SQLiteOpenHelper {


    public static final String LIFTER = "Lifter";
    public static final String COLUMN_NAME = "column_name";
    public static final String COLUMN_SEX = "column_sex";
    public static final String COLUMN_EVENT = "column_event";
    public static final String COLUMN_EQUIPMENT = "column_equipment";
    public static final String COLUMN_AGE = "column_age";
    public static final String COLUMN_AGE_CLASS = "column_age_class";
    public static final String COLUMN_BIRTH_YEAR = "column_birth_year";
    public static final String COLUMN_DIVISION = "column_division";
    public static final String COLUMN_BW = "column_bw";
    public static final String COLUMN_WEIGHTCLASS = "column_weightclass";
    public static final String COLUMN_SQUAT_1 = "column_squat1";
    public static final String COLUMN_SQUAT_2 = "column_squat2";
    public static final String COLUMN_SQUAT_3 = "column_squat3";
    public static final String COLUMN_SQUAT_4 = "column_squat4";
    public static final String COLUMN_BEST_SQUAT = "column_best_squat";
    public static final String COLUMN_BENCH_1 = "column_bench1";
    public static final String COLUMN_BENCH_2 = "column_bench2";
    public static final String COLUMN_BENCH_3 = "column_bench3";
    public static final String COLUMN_BENCH_4 = "column_bench4";
    public static final String COLUMN_BEST_BENCHPRESS = "column_best_benchpress";
    public static final String COLUMN_DEADLIFT_1 = "column_deadlift1";
    public static final String COLUMN_DEADLIFT_2 = "column_deadlift2";
    public static final String COLUMN_DEADLIFT_3 = "column_deadlift3";
    public static final String COLUMN_DEADLIFT_4 = "column_deadlift4";
    public static final String COLUMN_BESTDEADLIFT = "column_bestdeadlift";
    public static final String COLUMN_TOTAL = "column_total";
    public static final String COLUMN_PLACE = "column_place";
    public static final String COLUMN_DOTS = "column_dots";
    public static final String COLUMN_WILKS = "column_wilks";
    public static final String COLUMN_GLOSSBREN = "column_glossbren";
    public static final String COLUMN_GOODLIFT = "column_goodlift";
    public static final String COLUMN_TESTED = "column_tested";
    public static final String COLUMN_COUNTRY = "column_country";
    public static final String COLUMN_STATE = "column_state";
    public static final String COLUMN_FEDERATION = "column_federation";
    public static final String COLUMN_PARENT_FEDERATION = "column_parent_federation";
    public static final String COLUMN_DATE = "column_date";
    public static final String COLUMN_MEET_COUNTRY = "column_meet_country";
    public static final String COLUMN_MEET_STATE = "column_meet_state";
    public static final String COLUMN_MEETTOWN = "column_meettown";
    public static final String COLUMN_MEETNAME = "column_meetname";
    public static final String LIFTER1 = "LIFTER";

    public DataBaseStatistic(@Nullable Context context) {
        super(context, "nnn.db", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String x = "CREATE TABLE " + LIFTER + "(" + COLUMN_NAME + " TEXT, " + COLUMN_SEX + " text, " + COLUMN_EVENT + " text, " + COLUMN_EQUIPMENT + " TEXT, " + COLUMN_AGE + " INT, " + COLUMN_AGE_CLASS + " REAL, " + COLUMN_BIRTH_YEAR + " REAL, " + COLUMN_DIVISION + " TEXT, " + COLUMN_BW + " REAL, " + COLUMN_WEIGHTCLASS + " REAL, " + COLUMN_SQUAT_1 + " REAL, " + COLUMN_SQUAT_2 + " REAL, " + COLUMN_SQUAT_3 + " REAL, " + COLUMN_SQUAT_4 + " REAL, " + COLUMN_BEST_SQUAT + " REAL, " + COLUMN_BENCH_1 + " REAL, " + COLUMN_BENCH_2 + " REAL, " + COLUMN_BENCH_3 + " REAL, " + COLUMN_BENCH_4 + " REAL, " + COLUMN_BEST_BENCHPRESS + " REAL, " + COLUMN_DEADLIFT_1 + " REAL, " + COLUMN_DEADLIFT_2 + " REAL, " + COLUMN_DEADLIFT_3 + " REAL, " + COLUMN_DEADLIFT_4 + " REAL, " + COLUMN_BESTDEADLIFT + " REAL, " + COLUMN_TOTAL + " REAL, " + COLUMN_PLACE + " INT, " + COLUMN_DOTS + " REAL, " + COLUMN_WILKS + " REAL, " + COLUMN_GLOSSBREN + " REAL, " + COLUMN_GOODLIFT + " REAL, " + COLUMN_TESTED + " REAL, " + COLUMN_COUNTRY + " TEXT, " + COLUMN_STATE + " TEXT, " + COLUMN_FEDERATION + " TEXT, " + COLUMN_PARENT_FEDERATION + " TEXT, " + COLUMN_DATE + " REAL, " + COLUMN_MEET_COUNTRY + " TEXT, " + COLUMN_MEET_STATE + " TEXT, " + COLUMN_MEETTOWN + " TEXT, " + COLUMN_MEETNAME + " TEXT)";
        db.execSQL(x);
    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


    }

    ArrayList<Double> profile() {
        ArrayList<Double> xy = new ArrayList<Double>();
        xy.add(500.0);

        return xy;
    }

    ArrayList<ProfileStats> profileSQL() {
        double name;
        String date3 = "22.2009.300";
        String date = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String x = "select * from " + LIFTER;

        ArrayList<ProfileStats> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(x, null);

        int anzahl= 0;

        if (cursor.moveToFirst())
            do{

                name = Double.parseDouble(cursor.getString(14));
                date = cursor.getString(36);
                String[] columns = date.split("\\.");
                String date1 = columns[2];


                if(name != 0) {

                    storeContacts.add(new ProfileStats(name, date1));
                }
                anzahl++;
            }while(cursor.moveToNext());

        cursor.close();
        return storeContacts;

    }

    void cleartable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LIFTER,null,null);
    }

    void csvcopy(Context contex, String openName){
        SQLiteDatabase db = this.getWritableDatabase();

        String x = openName;

        String myCSVFile = openName + ".csv";
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
                if(columns.length != 41) {

                    continue;
                }

                ContentValues cv = new ContentValues();
                cv.put(COLUMN_DATE, columns[36].trim());
                cv.put(COLUMN_BEST_SQUAT, columns[14]);

                db.insert(LIFTER,null,cv);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }




}
