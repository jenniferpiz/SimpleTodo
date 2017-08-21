package com.codepath.jennifergodinez.simpletodo;

/**
 * Created by jennifergodinez on 8/20/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
// ...

public class TodoDialogFragment extends DialogFragment implements OnEditorActionListener {

    private EditText mEditText;
    private TodoDialogListener listener;

    public TodoDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
        this.listener = null;
    }

    public interface TodoDialogListener {
        void onFinishEditDialog(String name, int pos);
    }

    public static TodoDialogFragment newInstance(String title, String name, String date, String priority, int pos) {
        TodoDialogFragment frag = new TodoDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("name", name);
        args.putString("date", date);
        args.putString("priority", priority);
        args.putInt("pos", pos);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_todo, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        String name = getArguments().getString("name", "task 1");
        mEditText.setText(name);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mEditText.setOnEditorActionListener(this);
    }

    public void setCustomObjectListener(TodoDialogListener listener) {
        this.listener = listener;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            listener.onFinishEditDialog(mEditText.getText().toString(), getArguments().getInt("pos"));
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    /*
    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TodoDialogListener) {
            listener = (TodoDialogListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.TodoDialogListener");
        }
    }

    // Now we can fire the event when the user selects something in the fragment
    public void onSomeClick(View v) {
        listener.onRssItemSelected("some link");
    }
*/
}