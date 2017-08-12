package com.codepath.jennifergodinez.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String itemName = getIntent().getStringExtra("itemName");
        pos = getIntent().getIntExtra("pos", 0);
        EditText editText = (EditText)findViewById(R.id.etEditItem);
        editText.setText(itemName);
        editText.requestFocus();
    }

    public void onEditItem(View v) {
        EditText etName = (EditText) findViewById(R.id.etEditItem);
        Intent data = new Intent();
        data.putExtra("itemName", etName.getText().toString());
        data.putExtra("pos", pos);
        setResult(RESULT_OK, data);
        this.finish();
    }
}

