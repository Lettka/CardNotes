package com.example.cardnotes;

import android.app.DatePickerDialog;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NoteContentFragment extends Fragment {

    public static final String CURRENT_NOTE = "CURRENT_NOTE";
    private CardNote note;
    final Calendar myCalendar = Calendar.getInstance();

    public NoteContentFragment() {
        // Required empty public constructor
    }


    public static NoteContentFragment newInstance(CardNote note) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(CURRENT_NOTE);
        } else {
            note = new CardNote();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().popBackStack());

        DatePickerDialog.OnDateSetListener datePicker = (view1, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(view);
        };

        CheckBox like = view.findViewById(R.id.noteLike);
        like.setOnClickListener(v -> {
            note.setLike(!note.isLike());
        });

        view.findViewById(R.id.note_date).setOnClickListener(v ->
                new DatePickerDialog(getContext(), datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        initView(view);
    }

    private void updateLabel(View view) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        TextView date = view.findViewById(R.id.note_date);
        date.setText(sdf.format(myCalendar.getTime()));
        note.setDate(String.valueOf(date.getText()));
    }

    private void initView(View view) {
        ImageView imageView = view.findViewById(R.id.note_image_view);
        CheckBox like = view.findViewById(R.id.noteLike);
        TextView date = view.findViewById(R.id.note_date);
        TextView title = view.findViewById(R.id.noteTitle);
        TextView description = view.findViewById(R.id.notesContext);

        imageView.setImageResource(note.getImage());
        like.setChecked(note.isLike());
        date.setText(note.getDate());
        title.setText(note.getTitleNotes());
        description.setText(note.getContextNotes());

    }

}