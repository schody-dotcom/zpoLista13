package com.example.edu.sqliteprzyklad;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
    }

    public void onAddData(View view) {
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        EditText editText = findViewById(R.id.enterCountry);

        String url = "https://coronavirus-19-api.herokuapp.com/countries/"+(editText.getText()+"/");

        RequestQueue queue = Volley.newRequestQueue(this);

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                COVIDData covidData = gson.fromJson(response, COVIDData.class);


                SQLiteDatabase database = openOrCreateDatabase("COVID", MODE_PRIVATE, null);
                String sqlDB = "CREATE TABLE IF NOT EXISTS COVID (country VARCHAR, cases INTEGER, active INTEGER, casesPerOneMilion INTEGER, testsPerOneMilion INTEGER)";
                database.execSQL(sqlDB);

                String sqlCOVID = "INSERT INTO COVID VALUES (?,?,?,?,?)";
                SQLiteStatement statement = database.compileStatement(sqlCOVID);

                statement.bindString(1, covidData.getCountry());
                statement.bindLong(2, covidData.getCases());
                statement.bindLong(3, covidData.getActive());
                statement.bindLong(4, covidData.getCasesPerOneMillion());
                statement.bindLong(5, covidData.getTestsPerOneMillion());
                statement.executeInsert();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}


