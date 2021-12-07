package com.example.cardnotes;

public interface OnDialogListener {
    String getTitleNotes();

    void setTitleNotes(String titleNotes);

    String getContextNotes();

    void setContextNotes(String contextNotes);

    void updateDataNote(String titleNotes, String contextNotes);
}
