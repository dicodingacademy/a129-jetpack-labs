package com.dicoding.picodiploma.mynoteapps.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "note_database")
                            .build();
                    add();
                }
            }
        }
        return INSTANCE;
    }

    private static void add() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final List<Note> list = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    list.add(new Note("Tugas " + i, "Belajar Modul " + i, ""));
                }
                INSTANCE.noteDao().insertAll(list);
            }
        });
    }
}