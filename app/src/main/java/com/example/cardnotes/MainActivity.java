package com.example.cardnotes;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class MainActivity extends AppCompatActivity {

    private CardSource source;
    private CardsAdapter adapter;
    private RecyclerView recyclerView;
    private OnDialogListener dialogListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        source = new CardSourceImp(this);

        adapter = new CardsAdapter(this, source);
        adapter.setClickListener((view, position) -> {
            NoteContentFragment fragment = NoteContentFragment.newInstance(source.getCardNote(position));
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            adapter.setMenuPosition(position);
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(AppCompatResources.getDrawable(this, R.drawable.separator)));

        LandingAnimator animator = new LandingAnimator();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(animator);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);

        dialogListener = new OnDialogListener() {

            @Override
            public String getTitleNotes() {
                return source.getCardNote(adapter.getMenuPosition()).getTitleNotes();
            }

            @Override
            public void setTitleNotes(String titleNotes) {
                source.getCardNote(adapter.getMenuPosition()).setTitleNotes(titleNotes);
            }

            @Override
            public String getContextNotes() {
                return source.getCardNote(adapter.getMenuPosition()).getContextNotes();
            }

            @Override
            public void setContextNotes(String contextNotes) {
                source.getCardNote(adapter.getMenuPosition()).setContextNotes(contextNotes);
            }
        };

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_update) {
            MyDialogFragment dialogFragment = MyDialogFragment.newInstance();
            dialogFragment.setOnDialogListener(dialogListener);
            dialogFragment.show(getSupportFragmentManager(), "dialog_fragment");
            source.updateNote(adapter.getMenuPosition(), dialogListener.getTitleNotes(), dialogListener.getContextNotes());
            adapter.notifyItemChanged(adapter.getMenuPosition());
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            source.deleteNote(adapter.getMenuPosition());
            adapter.notifyItemRemoved(adapter.getMenuPosition());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem1 = menu.findItem(R.id.action_add);
        MenuItem menuItem2 = menu.findItem(R.id.action_clear);
        MenuItem menuItem3 = menu.findItem(R.id.action_delete_note);
        if (menuItem1 != null) {
            menuItem1.setVisible(true);
        }
        if (menuItem2 != null) {
            menuItem2.setVisible(true);
        }
        if (menuItem3 != null) {
            menuItem3.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            source.addNote(new CardNote());
            adapter.setMenuPosition(source.size() - 1);
            MyDialogFragment dialogFragment = MyDialogFragment.newInstance();
            dialogFragment.setOnDialogListener(dialogListener);
            dialogFragment.show(getSupportFragmentManager(), "dialog_fragment");
            adapter.notifyItemInserted(source.size() - 1);
            recyclerView.scrollToPosition(source.size() - 1);
            return true;
        } else if (item.getItemId() == R.id.action_clear) {
            int size = source.size();
            source.clearNotes();
            adapter.notifyItemRangeRemoved(0, size);
            return true;
        } else if (item.getItemId() == R.id.action_delete_note) {
            source.deleteNote(adapter.getMenuPosition());
            adapter.notifyItemRemoved(adapter.getMenuPosition());
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}