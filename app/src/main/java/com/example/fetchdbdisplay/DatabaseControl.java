package com.example.fetchdbdisplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DatabaseControl extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "hiring";

    public DatabaseControl(Context context) {
        super(context, "Interview", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id int, listid int, name varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }
    public void addData(JSONArray a) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Flush data
            this.onUpgrade(db, 1, 1);
            this.onCreate(db);
            ContentValues content = new ContentValues();

            try {
                for(int i = 0; i < a.length(); i++) {
                    //Current JSON object
                    JSONObject o = a.getJSONObject(i);
                    // Add id, listId, and name of JSON object
                    content.put("id", o.getInt("id"));
                    content.put("listid", o.getInt("listId"));
                    content.put("name", o.getString("name"));
                    db.insert(TABLE_NAME, null, content);
                }
            } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
    public Cursor getQueryResult(String q) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(q, null);
        return result;
    }
}
