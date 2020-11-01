package com.example.fetchdbdisplay;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button query1, query2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_activity);

        // Instantiate buttons
        query1 = (Button) findViewById(R.id.query1);
        query2 = (Button) findViewById(R.id.query2);

        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQueryListctivity("select count(id), listid from hiring" +
                                          " group by listid");
            }
        });

        query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQueryListctivity("select * from hiring" +
                                          " where name <> '' and name <> 'null'" +
                                          " order by listid, name");
            }
        });

        // Query 2

    }
    void startQueryListctivity(String q) {
        Intent intent = new Intent(this, QueryList.class);
        intent.putExtra("dynamicQuery", q);
        startActivity(intent);
    }
}
