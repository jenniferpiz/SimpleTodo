package com.codepath.jennifergodinez.simpletodo;

/**
 * Created by jennifergodinez on 8/20/17.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private EditText etDate;
    private EditText etPriority;
    private TodoDialogListener listener;

    public TodoDialogFragment() {
        this.listener = null;
    }

    public interface TodoDialogListener {
        void onFinishEditDialog(String name, String dueDate, int pos);
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
        mEditText = (EditText) view.findViewById(R.id.txt_todo_name);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Item Below");
        getDialog().setTitle(title);
        String name = getArguments().getString("name", "default task");
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
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
            b.setMessage("Do you want to set the Due Date?");
            //final EditText input = new EditText(getActivity());
            //b.setView(input);
            b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //result = input.getText().toString();
                    if (dialog != null)  {
                        listener.onFinishEditDialog(mEditText.getText().toString(), "21 Aug 1995", getArguments().getInt("pos"));
                        dialog.dismiss();
                    }
                }
            });
            b.setNegativeButton("NO",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialog != null)  {
                        listener.onFinishEditDialog(mEditText.getText().toString(), "", getArguments().getInt("pos"));
                        dialog.dismiss();
                    }
                }
            });
            b.show();
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

}