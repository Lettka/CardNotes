package com.example.cardnotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    private OnDialogListener dialogListener;

    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    public void setOnDialogListener(OnDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.set_data, container, false);
        EditText titleNote = view.findViewById(R.id.set_title);
        EditText contextNote = view.findViewById(R.id.set_context);
        titleNote.setText(dialogListener.getTitleNotes());
        contextNote.setText(dialogListener.getContextNotes());
        view.findViewById(R.id.save_data).setOnClickListener(v -> {
            dismiss();
            dialogListener.setTitleNotes(titleNote.getText().toString());
            dialogListener.setContextNotes(contextNote.getText().toString());
            dialogListener.updateDataNote(titleNote.getText().toString(), contextNote.getText().toString());
        });
        return view;
    }
}