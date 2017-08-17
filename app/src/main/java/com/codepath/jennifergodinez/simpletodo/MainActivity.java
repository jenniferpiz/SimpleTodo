package com.codepath.jennifergodinez.simpletodo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {
    ArrayList<ToDo> todoArray;
    TodosAdapter todosAdapter;
    ListView lvItems;
    static final int EDIT_ITEM_REQUEST = 100;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup database
        PracticeDatabaseHelper dbHelper = new PracticeDatabaseHelper(this);
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        db = dbHelper.getWritableDatabase();

        // init todoArray
        final QueryResultIterable<ToDo> iter = cupboard().withDatabase(db).query(ToDo.class).query();
        todoArray = (ArrayList<ToDo>) getListFromQueryResultIterator(iter);

        //set-up ListView
        todosAdapter = new TodosAdapter(this, todoArray);
        lvItems = (ListView)findViewById(R.id.lvItems);
        lvItems.setAdapter(todosAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos, long id) {
                ToDo td = todoArray.get(pos);
                cupboard().withDatabase(db).delete(ToDo.class, td.get_id());
                todoArray.remove(pos);
                //tnames.remove(pos);
                //tnamesAdapter.notifyDataSetChanged();
                todosAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {
                ToDo value = (ToDo) adapterView.getItemAtPosition(pos);
                launchEditActivity(value.name, value.date, value.priority, pos);
            }
        });
    }

    private void launchEditActivity(String name, String date, String priority, int pos) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("name", name);
        i.putExtra("date", date);
        i.putExtra("priority", priority);
        i.putExtra("pos", pos);
        startActivityForResult(i, EDIT_ITEM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST) {
            // Update the selected item
            String newName = i.getExtras().getString("name");
            //String newDate = i.getExtras().getString("date");
            //String newPriority = i.getExtras().getString("priority");
            int pos = i.getExtras().getInt("pos", 0);
            ToDo td = todoArray.get(pos);
            td.setName(newName);
            //td.setDate(newDate);
            //td.setPriority(newPriority);
            cupboard().withDatabase(db).put(td);
            todosAdapter.notifyDataSetChanged();
        }
    }

    public void onAddItem(View vew) {
        EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        ToDo toDo = new ToDo(itemText);
        todoArray.add(toDo);
        cupboard().withDatabase(db).put(toDo);
        etNewItem.setText("");
        todosAdapter.notifyDataSetChanged();
    }

    private static List<ToDo> getListFromQueryResultIterator(QueryResultIterable<ToDo> iter) {
        final List<ToDo> items = new ArrayList<ToDo>();
        for (ToDo item : iter) {
            items.add(item);
        }
        iter.close();

        return items;
    }
}
