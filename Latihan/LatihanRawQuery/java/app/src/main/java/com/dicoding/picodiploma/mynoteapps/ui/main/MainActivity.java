package com.dicoding.picodiploma.mynoteapps.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.mynoteapps.R;
import com.dicoding.picodiploma.mynoteapps.database.Note;
import com.dicoding.picodiploma.mynoteapps.helper.SortUtils;
import com.dicoding.picodiploma.mynoteapps.helper.ViewModelFactory;
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity.REQUEST_UPDATE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private NotePagedListAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = obtainViewModel(MainActivity.this);

        mainViewModel.getAllNotes(SortUtils.NEWEST).observe(this, noteObserver);

        adapter = new NotePagedListAdapter(MainActivity.this);

        recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab_add) {
                    Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
                    startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added));
                }
            } else if (requestCode == REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed));
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted));
                }
            }
        }
    }

    private final Observer<PagedList<Note>> noteObserver = new Observer<PagedList<Note>>() {
        @Override
        public void onChanged(@Nullable PagedList<Note> noteList) {
            if (noteList != null) {
                adapter.submitList(noteList);
            }
        }
    };

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String sort = SortUtils.NEWEST;
        switch (item.getItemId()) {
            case R.id.action_newest:
                sort = SortUtils.NEWEST;
                break;
            case R.id.action_oldest:
                sort = SortUtils.OLDEST;
                break;
            case R.id.action_random:
                sort = SortUtils.RANDOM;
                break;
        }
        mainViewModel.getAllNotes(sort).observe(this, noteObserver);
        item.setChecked(true);
        return super.onOptionsItemSelected(item);
    }
}
