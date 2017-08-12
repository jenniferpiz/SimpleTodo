package com.codepath.jennifergodinez.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    static final int EDIT_ITEM_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
        //items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items );
        lvItems.setAdapter(itemsAdapter);
        //items.add("First Item");
        //items.add("Second Item");
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {
                items.remove(pos);
                writeItems();
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {
                String value = (String)adapterView.getItemAtPosition(pos);
                launchEditActivity(value, pos);
                return ;
            }
        });
    }

    public void launchEditActivity(String itemName, int pos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("itemName", itemName);
        i.putExtra("pos", pos);
        startActivityForResult(i, EDIT_ITEM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST) {
            // Extract edited item from result extras
            String editItem = i.getExtras().getString("itemName");
            int pos = i.getExtras().getInt("pos", 0);
            items.remove(items.get(pos));
            items.add(pos, editItem);
            writeItems();
            itemsAdapter.notifyDataSetChanged();
        }
    }

    public void onAddItem(View vew) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        writeItems();
        etNewItem.setText("");
    }

    private void writeItems() {
        File dir = getFilesDir();
        File todoFile = new File(dir, "todo.txt");

        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readItems() {
        File dir = getFilesDir();
        File todoFile = new File(dir, "todo.txt");

        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException ioe) {
            items = new ArrayList<String>();
        }
    }
}
