package com.example.cardnotes;

public interface CardSource {
    CardNote getCardNote(int position);
    int size();
    void deleteNote(int position);
    void updateNote(int position, String title, String context);
    void addNote(CardNote note);
    void clearNotes();
    void updateList(FirebaseCardSource.OnInitListener onInitListener);
}
