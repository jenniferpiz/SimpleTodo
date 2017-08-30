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
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);

        // Populate the data into the template view using the data object
        ToDo toDo = getItem(position);
        if (toDo != null) {
            tvName.setText(toDo.name);
            if (toDo.priority.equals("HIGH")) {
                tvName.setBackgroundColor(Color.RED);
            } else if (toDo.priority.equals("MEDIUM")) {
                tvName.setBackgroundColor(Color.YELLOW);
            } else if (toDo.priority.equals("LOW")) {
                tvName.setBackgroundColor(Color.GREEN);
            }

            tvDate.setText(toDo.date);

            //change appearance
            DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
            tvName.setTextSize(20);
            tvDate.setTextSize(16);
            tvDate.setTextColor(Color.BLACK);

        }
        // Return the completed view to render on screen
        return convertView;
    }
}
