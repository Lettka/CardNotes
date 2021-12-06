package com.example.cardnotes;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCardSource implements CardSource {

    private static final String NOTES_COLLECTION = "notes";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection = db.collection(NOTES_COLLECTION);
    private List<CardNote> notesList = new ArrayList<>();

    public FirebaseCardSource(OnInitListener onInitListener) {
        collection.orderBy("titleNotes")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        notesList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            CardNote note = documentSnapshot.toObject(CardNote.class);
                            notesList.add(note);
                        }
                        Log.d("DEBUGLOG", "Successful" + notesList.toString());
                        onInitListener.onInit();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("DEBUGLOG", "error", e);
                });
    }

    @Override
    public CardNote getCardNote(int position) {
        return notesList.get(position);
    }

    @Override
    public int size() {
        return notesList.size();
    }

    @Override
    public void deleteNote(int position) {
        collection.document(notesList.get(position).getId()).delete();
        notesList.remove(position);
    }

    @Override
    public void updateNote(int position, String title, String context) {
        notesList.get(position).setTitleNotes(title);
        notesList.get(position).setContextNotes(context);
        collection.document(notesList.get(position).getId()).set(notesList.get(position));
    }

    @Override
    public void addNote(CardNote note) {
        collection.add(note);
        notesList.add(note);
    }

    @Override
    public void clearNotes() {
        for (CardNote note :
                notesList) {
            collection.document(note.getId()).delete();
        }
        notesList.clear();
    }

    interface OnInitListener {
        void onInit();
    }
}
