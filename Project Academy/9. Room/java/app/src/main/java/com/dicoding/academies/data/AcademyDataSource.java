package com.dicoding.academies.data;


import androidx.lifecycle.LiveData;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import java.util.List;

public interface AcademyDataSource {

    LiveData<List<CourseEntity>> getAllCourses();

    LiveData<CourseEntity> getCourseWithModules(String courseId);

    LiveData<List<ModuleEntity>> getAllModulesByCourse(String courseId);

    LiveData<List<CourseEntity>> getBookmarkedCourses();

    LiveData<ModuleEntity> getContent(String courseId, String moduleId);
}