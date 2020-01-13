package com.dicoding.picodiploma.mynoteapps.repository;

import android.app.Application;

import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.dicoding.picodiploma.mynoteapps.database.Note;
import com.dicoding.picodiploma.mynoteapps.database.NoteDao;
import com.dicoding.picodiploma.mynoteapps.database.NoteRoomDatabase;
import com.dicoding.picodiploma.mynoteapps.helper.SortUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDao mNotesDao;
    private ExecutorService executorService;
    public NoteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNotesDao = db.noteDao();
    }

    public DataSource.Factory<Integer, Note> getAllNotes(String sort) {
        SimpleSQLiteQuery query = SortUtils.getSortedQuery(sort);
        return mNotesDao.getAllNotes(query);
    }

    public void insert(final Note note) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.insert(note);
            }
        });
    }

    public void delete(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.delete(note);
            }
        });
    }

    public void update(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.update(note);
            }
        });
    }
}