package com.dicoding.academies.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import java.util.List;

@Dao
public interface AcademyDao {

    @WorkerThread
    @Query("SELECT * FROM courseentities")
    LiveData<List<CourseEntity>> getCourses();

    @WorkerThread
    @Query("SELECT * FROM courseentities where bookmarked = 1")
    LiveData<List<CourseEntity>> getBookmarkedCourse();

    @Transaction
    @Query("SELECT * FROM courseentities WHERE courseId = :courseId")
    LiveData<CourseWithModule> getCourseWithModuleById(String courseId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCourses(List<CourseEntity> courses);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateCourse(CourseEntity course);

    @Query("SELECT * FROM moduleentities WHERE courseId = :courseId")
    LiveData<List<ModuleEntity>> getModulesByCourseId(String courseId);

    @Query("SELECT * FROM moduleentities WHERE moduleId = :moduleId")
    LiveData<ModuleEntity> getModuleById(String moduleId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertModules(List<ModuleEntity> module);

    @Update
    int updateModule(ModuleEntity module);

    @Query("UPDATE moduleentities SET content = :content WHERE moduleId = :moduleId")
    int updateModuleByContent(String content, String moduleId);
}
