package com.dicoding.academies.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.ModuleEntity

@Database(entities = [CourseEntity::class, ModuleEntity::class], version = 1, exportSchema = false)
abstract class AcademyDatabase : RoomDatabase() {
    abstract fun academyDao(): AcademyDao

    companion object {

        @Volatile
        private var INSTANCE: AcademyDatabase? = null

        fun getInstance(context: Context): AcademyDatabase {
            if (INSTANCE == null) {
                synchronized(AcademyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AcademyDatabase::class.java, "Academies.db")
                                .build()
                    }
                }
            }
            return INSTANCE as AcademyDatabase
        }
    }
}