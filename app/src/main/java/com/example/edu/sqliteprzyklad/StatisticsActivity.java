package com.example.edu.sqliteprzyklad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.TextView;


public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        TextView tvSuma = findViewById(R.id.suma);
        TextView tvProcent = findViewById(R.id.procent);
        TextView tvTesty = findViewById(R.id.testy);


        SQLiteDatabase database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country VARCHAR, cases INTEGER, active INTEGER, casesPerOneMilion INTEGER, testsPerOneMilion INTEGER)";
        database.execSQL(sqlDB);

        Cursor c = database.rawQuery("SELECT SUM(cases) FROM COVID", null);
        c.moveToFirst();
        tvSuma.setText("SUM OF CASES: "+c.getInt(0));



        c = database.rawQuery("SELECT country FROM COVID ORDER BY ((cases-active)*1.0/(1.0*cases)) DESC ", null);
        c.moveToFirst();
        tvProcent.setText("HIGHEST CURED PERCENT IN: "+c.getString(0));


        c = database.rawQuery("SELECT country  FROM COVID ORDER BY testsPerOneMilion DESC ",null);

        String s ="";
        if(c.moveToFirst()){

            do {
                s = s+c.getString(0)+", ";
            } while(c.moveToNext());
        }
        tvTesty.setText("TESTS PER ONE MILLION (DESCENDING): " + s);
        c.close();
    }
}