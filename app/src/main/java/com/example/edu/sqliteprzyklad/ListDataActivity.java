package com.example.edu.sqliteprzyklad;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    ArrayList<COVIDData> covidData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);



        RecyclerView rvData = (RecyclerView) findViewById(R.id.rvData);

        // Initialize contacts
        covidData = new ArrayList<>();

        SQLiteDatabase database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country VARCHAR, cases INTEGER, active INTEGER, casesPerOneMilion INTEGER, testsPerOneMilion INTEGER)";
        database.execSQL(sqlDB);


        Cursor c = database.rawQuery("SELECT country, cases, active, casesPerOneMilion, testsPerOneMilion FROM COVID",null);

        if(c.moveToFirst()){

            do {

                String country = c.getString(c.getColumnIndex("country"));
                int cases = Integer.parseInt(c.getString(c.getColumnIndex("cases")));
                int active = Integer.parseInt(c.getString(c.getColumnIndex("active")));
                int cm = Integer.parseInt(c.getString(c.getColumnIndex("casesPerOneMilion")));
                int tm = Integer.parseInt(c.getString(c.getColumnIndex("testsPerOneMilion")));


                covidData.add(new COVIDData(country, cases, active, cm, tm));

            } while(c.moveToNext());
        }


        dbAdapter adapter = new dbAdapter(covidData);

        rvData.setAdapter((RecyclerView.Adapter) adapter);

        rvData.setLayoutManager(new LinearLayoutManager(this));
    }
    }
