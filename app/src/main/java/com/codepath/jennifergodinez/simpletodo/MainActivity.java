package com.codepath.jennifergodinez.simpletodo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
                todosAdapter.notifyDataSetChanged();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {
                ToDo value = (ToDo) adapterView.getItemAtPosition(pos);
                showEditDialog(value.name, value.date, value.priority, pos);
            }
        });
    }

    public void onAddItem(View view) {
        final FragmentManager fm = getSupportFragmentManager();
        TodoDialogFragment editTodoDialogFragment = TodoDialogFragment.newInstance("Add Item", "", "", "", 0);

        // Setup the listener for this object
        editTodoDialogFragment.setCustomObjectListener(new TodoDialogFragment.TodoDialogListener() {
            @Override
            public void onFinishEditDialog(String newName, String newDate, int pos) {
                ToDo toDo = new ToDo(newName, newDate);
                todoArray.add(toDo);
                cupboard().withDatabase(db).put(toDo);
                todosAdapter.notifyDataSetChanged();

            }
        });
        editTodoDialogFragment.show(fm, "fragment_edit_todo");
    }

    private void showEditDialog(String name, String date, String priority, int pos) {
        final FragmentManager fm = getSupportFragmentManager();
        TodoDialogFragment editTodoDialogFragment = TodoDialogFragment.newInstance("Some Title", name, date, priority, pos);

        // Setup the listener for this object
        editTodoDialogFragment.setCustomObjectListener(new TodoDialogFragment.TodoDialogListener() {
            @Override
            public void onFinishEditDialog(String newName, String newDate, int pos) {
                ToDo td = todoArray.get(pos);
                td.setName(newName);
                td.setDate(newDate);
                cupboard().withDatabase(db).put(td);
                todosAdapter.notifyDataSetChanged();
            }
        });
        editTodoDialogFragment.show(fm, "fragment_edit_todo");

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
