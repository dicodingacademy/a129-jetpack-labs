package com.dicoding.picodiploma.mynoteapps.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Update()
    void update(Note note);

    @Delete()
    void delete(Note note);

    @Query("SELECT * from note ORDER BY id ASC")
    DataSource.Factory<Integer, Note> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Note> list);
}