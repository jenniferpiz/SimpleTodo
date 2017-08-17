package com.codepath.jennifergodinez.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jennifergodinez on 8/16/17.
 */

class TodosAdapter extends ArrayAdapter<ToDo> {
    public TodosAdapter(Context context, ArrayList<ToDo> toDos) {
        super(context, 0, toDos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);

        // Populate the data into the template view using the data object
        ToDo toDo = getItem(position);
        if (toDo != null) {
            tvName.setText(toDo.name);
            tvDate.setText(toDo.date);
        }

        //change appearance
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float Textsize =tvName.getTextSize()/metrics.density;
        tvName.setTextSize(20);
        tvName.setTextColor(Color.GREEN);
        tvDate.setTextSize(10);
        tvDate.setTextColor(Color.BLACK);
        convertView.setBackgroundColor(Color.GRAY);

        // Return the completed view to render on screen
        return convertView;
    }
}
