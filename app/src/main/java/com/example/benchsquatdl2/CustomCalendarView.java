package com.example.benchsquatdl2;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.benchsquatdl2.model.modelApi.trainingdto;
import com.example.benchsquatdl2.retrofit.RetrofitService;
import com.example.benchsquatdl2.retrofit.UserApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCalendarView extends LinearLayout {

    ImageButton NextButton,PreviousButton;
    TextView CurrentDate;
    MyGridAdapter myGridAdapter;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Calendar calendar2 = new GregorianCalendar();
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormate = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    int start= 0;


    String trainingday = "25-07-2023";

    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();



    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) throws ParseException {
        super(context, attrs);
        this.context = context;
        InitializeLayout();
        SetUpCalendar();

        Log.d("arraydate","" + dates.size());

        Date test;

        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, -1);
                try {
                    SetUpCalendar();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               calendar.add(Calendar.MONTH,1);
                try {
                    SetUpCalendar();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_layout,null);
               //Edittext, Textview 7:33



                addView.setBackgroundColor(0xFF0000FF);
                Log.d("passiertwtas","" + position);


                String date = dateFormat.format(dates.get(position));
            }
        });





    }

    private void InitializeLayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mycalendar, this);
        NextButton = view.findViewById(R.id.nextBtn);
        PreviousButton = view.findViewById(R.id.previousBtn2);
        CurrentDate = view.findViewById(R.id.current_date);
        gridView = view.findViewById(R.id.gridview);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void SetUpCalendar() throws ParseException {
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        //Calendar Day of Month ist richtig (aktuelles Datum), Danach wird es auf 1 gesetzt
        Log.d("dayofmonth","" +monthCalendar.get(Calendar.DAY_OF_MONTH));
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar2.clear(Calendar.ZONE_OFFSET);
        Log.d("dayofmonthh","onthCalendar.getTime()" +calendar2.getTime());




        int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) -2;
        //mit -2 kann ich die Tageszahlen verschieben.OUTPUT =5
        //Day of Week = An welcher stelle steht der tag,
        //DAY_OF_WEEK is the day of the week (7 days), DAY_OF_MONTH is the day of the month (<=31 days)

        //wichtig für das pinke feld
        //start 01.07, -FirstDayofMonth, wir gehen paar tage zurück
        //In der while schlafen fange wir paar tage früher an zu zählen, als der Monat anfängt
        //Bsp. 26,27,28,29,30, dann 01 bis max 42
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);
        Log.d("dayofmonth","-FirstDayOfMonth" +monthCalendar.getTime());



        while (dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            Date datee = monthCalendar.getTime();
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
            //monthcalendar, tag wird um eins erhöht
        }

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        userApi.getDatabyID().enqueue(new Callback<List<trainingdto>>() {
            @Override
            public void onResponse(Call<List<trainingdto>> call, Response<List<trainingdto>> response) {
                myGridAdapter = new MyGridAdapter(context,dates,calendar,eventsList,response.body());
                gridView.setAdapter(myGridAdapter);

            }

            @Override
            public void onFailure(Call<List<trainingdto>> call, Throwable t) {

            }
        });





    }
}
