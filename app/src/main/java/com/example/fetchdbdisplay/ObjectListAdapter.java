package com.example.fetchdbdisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ObjectListAdapter extends ArrayAdapter<TableRow> {
    private Context context;
    private int re;

    public ObjectListAdapter(Context c, int resource, ArrayList<TableRow> objects) {
        super(c, resource, objects);
        re = resource;

        context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the row information
        String id = Integer.toString(getItem(position).getId());
        String listid = Integer.toString(getItem(position).getListid());
        String name = getItem(position).getName();

        // Set the inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(re, parent, false);

        if (name.compareTo("NA") == 0) {
            // First query

            TextView tCount = (TextView) convertView.findViewById(R.id.countLabel);
            TextView tListId = (TextView) convertView.findViewById(R.id.listidLabel);

            // Change the row text for display
            tCount.setText(id);
            tListId.setText(listid);
        } else {
            TextView tId = (TextView) convertView.findViewById(R.id.idLabel);
            TextView tListId = (TextView) convertView.findViewById(R.id.listidLabel);
            TextView tName = (TextView) convertView.findViewById(R.id.nameLabel);

            // Change the row text for display
            tId.setText(id);
            tListId.setText(listid);
            tName.setText(name);
        }

        return convertView;
    }
}
