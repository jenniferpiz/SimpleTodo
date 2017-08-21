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
    String name = getIntent().getStringExtra("name");
    String date = getIntent().getStringExtra("date");
    String priority = getIntent().getStringExtra("priority");
    pos = getIntent().getIntExtra("pos", 0);
    EditText editText = (EditText)findViewById(R.id.etEditItem);
    editText.setText(name);
    editText.requestFocus();
}

public void onEditItem(View v) {
    EditText etName = (EditText) findViewById(R.id.etEditItem);
    Intent data = new Intent();
    data.putExtra("name", etName.getText().toString());
    data.putExtra("pos", pos);
    setResult(RESULT_OK, data);
    this.finish();
}
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        showEditDialog();
        TodoDialogFragment object = new TodoDialogFragment();
        // Setup the listener for this object
        object.setCustomObjectListener(new TodoDialogFragment.TodoDialogListener() {
            @Override
            public void onFinishEditDialog(String name) {
                Intent data = new Intent();
                data.putExtra("name", name);
                data.putExtra("pos", pos);
                setResult(RESULT_OK, data);
                //this.finish();

            }
        });
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TodoDialogFragment editTodoDialogFragment = TodoDialogFragment.newInstance("Some Title");
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String priority = getIntent().getStringExtra("priority");
        pos = getIntent().getIntExtra("pos", 0);
        editTodoDialogFragment.show(fm, "fragment_edit_todo");
    }
*/
}

