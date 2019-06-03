package com.dicoding.picodiploma.mynoteapps.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.dicoding.picodiploma.mynoteapps.database.Note;
import com.dicoding.picodiploma.mynoteapps.repository.NoteRepository;

public class MainViewModel extends ViewModel {

    private NoteRepository mNoteRepository;

    public MainViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    LiveData<PagedList<Note>> getAllNotes() {
        return new LivePagedListBuilder<>(
                mNoteRepository.getAllNotes(), 20).build();
    }
}
