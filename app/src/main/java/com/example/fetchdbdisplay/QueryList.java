package com.example.fetchdbdisplay;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QueryList extends AppCompatActivity {
    DatabaseControl db;
    ListView list;
    String query;
    int layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query = getIntent().getStringExtra("dynamicQuery");
        db = new DatabaseControl(this);
        layout = 0;

        // Field to hold the list
        list = (ListView) findViewById(R.id.listView);

        // ArrayList to hold query result values
        ArrayList<TableRow> al = new ArrayList<>();

        // Read json data
        String json = null;
        JSONArray obj = new JSONArray();
        try {
            InputStream is = this.getAssets().open("hiring.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            // Create array of JSON objects
            obj = new JSONArray(json);
            // Insert data into table
            db.addData(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Try to fetch the query result
        Cursor result = db.getQueryResult(query);
        if (result.getCount() ==0) {
            System.out.println("Error occurred when executing query");
        } else if (query.compareTo("select count(id), listid from hiring group by listid") == 0){
            // Using the cursor's iterator, traverse through the query result
            while(result.moveToNext()) {
                // Create a new row
                TableRow r = new TableRow(result.getInt(0),
                        result.getInt(1),
                        "NA");

                // Add to the arrayList
                al.add(r);
            }
            // Set the layout
            layout = R.layout.custom_list_group;
        }
        else {
            // Using the cursor's iterator, traverse through the query result
            while(result.moveToNext()) {
                // Create a new row
                TableRow r = new TableRow(result.getInt(0),
                        result.getInt(1),
                        result.getString(2));

                // Add to the arrayList
                al.add(r);
            }
            // Set the layout
            layout = R.layout.custom_list;
        }
        ObjectListAdapter adapter = new ObjectListAdapter(this, layout, al);
        list.setAdapter(adapter);
    }
}
