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
    private TextView mLabelText;
    private TodoDialogListener listener;

    public TodoDialogFragment() {
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

    public void setCustomObjectListener(TodoDialogListener listener) {
        this.listener = listener;
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
        mEditText = (EditText) view.findViewById(R.id.txt_todo_name);
        mLabelText = (TextView) view.findViewById(R.id.lbl_todo_name);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Item Below");
        mLabelText.setText(title);
        String name = getArguments().getString("name", "default task");
        mEditText.setText(name);

        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mEditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            listener.onFinishEditDialog(mEditText.getText().toString(), getArguments().getInt("pos"));
            dismiss();
            return true;
        }
        return false;
    }


}