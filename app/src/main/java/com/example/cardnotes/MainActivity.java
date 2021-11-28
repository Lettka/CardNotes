package com.example.cardnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_NOTE = "CURRENT_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<CardNote> notes = Arrays.asList(
                new CardNote(getString(R.string.movies), getString(R.string.movies_description), R.drawable.movie),
                new CardNote(getString(R.string.music), getString(R.string.music_description), R.drawable.music),
                new CardNote(getString(R.string.clothes), getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(getString(R.string.products), getString(R.string.products_description), R.drawable.products),
                new CardNote(getString(R.string.recipe), getString(R.string.recipe_description), R.drawable.recipe),
                new CardNote(getString(R.string.movies) + 2, getString(R.string.movies_description), R.drawable.movie),
                new CardNote(getString(R.string.music) + 2, getString(R.string.music_description), R.drawable.music),
                new CardNote(getString(R.string.clothes) + 2, getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(getString(R.string.products) + 2, getString(R.string.products_description), R.drawable.products),
                new CardNote(getString(R.string.recipe) + 2, getString(R.string.recipe_description), R.drawable.recipe),
                new CardNote(getString(R.string.movies) + 3, getString(R.string.movies_description), R.drawable.movie),
                new CardNote(getString(R.string.music) + 3, getString(R.string.music_description), R.drawable.music),
                new CardNote(getString(R.string.clothes) + 3, getString(R.string.clothes_description), R.drawable.clothes),
                new CardNote(getString(R.string.products) + 3, getString(R.string.products_description), R.drawable.products),
                new CardNote(getString(R.string.recipe) + 3, getString(R.string.recipe_description), R.drawable.recipe)
        );

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CardsAdapter adapter = new CardsAdapter(notes);
        adapter.setClickListener((view, position) -> {
            NoteContentFragment fragment = NoteContentFragment.newInstance(notes.get(position));
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(AppCompatResources.getDrawable(this, R.drawable.separator));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
    }
}