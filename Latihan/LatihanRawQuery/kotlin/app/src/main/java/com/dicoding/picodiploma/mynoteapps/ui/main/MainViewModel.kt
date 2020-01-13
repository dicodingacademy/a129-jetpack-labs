package com.dicoding.picodiploma.mynoteapps.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.mynoteapps.database.Note
import com.dicoding.picodiploma.mynoteapps.repository.NoteRepository


class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(sort: String): LiveData<PagedList<Note>> = LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 20).build()
}