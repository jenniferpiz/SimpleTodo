package com.codepath.jennifergodinez.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditItemActivity extends AppCompatActivity {
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String prio;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String priority = getIntent().getStringExtra("priority");
        pos = getIntent().getIntExtra("pos", 0);
        EditText editText = (EditText)findViewById(R.id.etEditItem);
        editText.setText(name);
        editText.requestFocus();
        EditText editDate = (EditText)findViewById(R.id.editDate);
        editDate.setText(date);

        RadioGroup priorityGroup = (RadioGroup) findViewById(R.id.radioPriority);

        if (priority.equals("HIGH")) {
            priorityGroup.check(R.id.rbHigh);
        } else if (priority.equals("MEDIUM")) {
            priorityGroup.check(R.id.rbMedium);

        } else if (priority.equals("LOW")) {
            priorityGroup.check(R.id.rbLow);
        }




    }

    public void onEditItem(View v) {
        EditText etName = (EditText) findViewById(R.id.etEditItem);
        EditText editDate = (EditText)findViewById(R.id.editDate);
        RadioButton rbHigh = (RadioButton)findViewById(R.id.rbHigh);
        RadioButton rbMedium = (RadioButton)findViewById(R.id.rbMedium);
        RadioButton rbLow = (RadioButton)findViewById(R.id.rbLow);
        Intent data = new Intent();
        data.putExtra("name", etName.getText().toString());
        data.putExtra("date", editDate.getText().toString());
        if (rbHigh.isChecked()) {
            data.putExtra("priority", "HIGH");
        } else if (rbMedium.isChecked()) {
            data.putExtra("priority", "MEDIUM");
        } else if (rbLow.isChecked()) {
            data.putExtra("priority", "LOW");
        }
        data.putExtra("pos", pos);
        setResult(RESULT_OK, data);
        this.finish();
    }
}

