package com.example.cardnotes;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardSourceImp implements CardSource {

    private List<CardNote> notes;

    public CardSourceImp(@NonNull Context context) {
        this.notes = new ArrayList<>(Arrays.asList(
                new CardNote(context.getString(R.string.movies), context.getString(R.string.movies_description), R.drawable.movie),
                new CardNote(context.getString(R.string.music), context.getString(R.string.music_description), R.drawable.music),
                new CardNote(context.getString(R.string.clothes), context.getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(context.getString(R.string.products), context.getString(R.string.products_description), R.drawable.products),
                new CardNote(context.getString(R.string.recipe), context.getString(R.string.recipe_description), R.drawable.recipe),
                new CardNote(context.getString(R.string.movies) + 2, context.getString(R.string.movies_description), R.drawable.movie),
                new CardNote(context.getString(R.string.music) + 2, context.getString(R.string.music_description), R.drawable.music),
                new CardNote(context.getString(R.string.clothes) + 2, context.getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(context.getString(R.string.products) + 2, context.getString(R.string.products_description), R.drawable.products),
                new CardNote(context.getString(R.string.recipe) + 2, context.getString(R.string.recipe_description), R.drawable.recipe),
                new CardNote(context.getString(R.string.movies) + 3, context.getString(R.string.movies_description), R.drawable.movie),
                new CardNote(context.getString(R.string.music) + 3, context.getString(R.string.music_description), R.drawable.music),
                new CardNote(context.getString(R.string.clothes) + 3, context.getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(context.getString(R.string.products) + 3, context.getString(R.string.products_description), R.drawable.products),
                new CardNote(context.getString(R.string.recipe) + 3, context.getString(R.string.recipe_description), R.drawable.recipe)
        ));
    }

    @Override
    public CardNote getCardNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteNote(int position) {
        notes.remove(position);
    }

    @Override
    public void updateNote(int position, String title, String context) {
        notes.get(position).setTitleNotes(title);
        notes.get(position).setContextNotes(context);
    }

    @Override
    public void addNote(CardNote note) {
        notes.add(note);
    }

    @Override
    public void clearNotes() {
        notes.clear();
    }
}
