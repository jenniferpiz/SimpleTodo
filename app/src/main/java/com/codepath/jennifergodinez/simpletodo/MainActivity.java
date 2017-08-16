package com.codepath.jennifergodinez.simpletodo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {
    ArrayList<ToDo> todoArray;
    ArrayList<String> tnames;
    ArrayAdapter<String> tnamesAdapter;
    ListView lvItems;
    static final int EDIT_ITEM_REQUEST = 100;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);

        // setup database
        PracticeDatabaseHelper dbHelper = new PracticeDatabaseHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        db = dbHelper.getWritableDatabase();

        tnames = getAllNames();
        tnamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tnames );
        
        lvItems.setAdapter(tnamesAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {
                ToDo td = todoArray.get(pos);
                cupboard().withDatabase(db).delete(ToDo.class, td.get_id());
                todoArray.remove(pos);
                tnames.remove(pos);
                tnamesAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {
                String value = (String)adapterView.getItemAtPosition(pos);
                launchEditActivity(value, pos);
            }
        });
    }

    private void launchEditActivity(String itemName, int pos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("itemName", itemName);
        i.putExtra("pos", pos);
        startActivityForResult(i, EDIT_ITEM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST) {
            // Update the item
            String editItem = i.getExtras().getString("itemName");
            int pos = i.getExtras().getInt("pos", 0);
            tnames.set(pos, editItem);

            ToDo td = todoArray.get(pos);
            td.setName(editItem);
            cupboard().withDatabase(db).put(td);
            tnamesAdapter.notifyDataSetChanged();
        }
    }

    public void onAddItem(View vew) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        tnamesAdapter.add(itemText);
        ToDo toDo = new ToDo(itemText);
        todoArray.add(toDo);
        cupboard().withDatabase(db).put(toDo);
        etNewItem.setText("");
    }

    private static List<ToDo> getListFromQueryResultIterator(QueryResultIterable<ToDo> iter) {
        final List<ToDo> items = new ArrayList<ToDo>();
        for (ToDo item : iter) {
            items.add(item);
        }
        iter.close();

        return items;
    }

    public ArrayList<String> getAllNames() {
        final QueryResultIterable<ToDo> iter = cupboard().withDatabase(db).query(ToDo.class).query();
        todoArray = (ArrayList<ToDo>) getListFromQueryResultIterator(iter);

        ArrayList<String> tnameArray = new ArrayList<String>();
        for (ToDo b : todoArray) {
            tnameArray.add(b.getName());
        }

        return tnameArray;
    }

}
