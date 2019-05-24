package com.dicoding.academies.data.source;

import androidx.lifecycle.LiveData;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import java.util.List;

public interface AcademyDataSource {

    // Untuk Halaman Academy
    LiveData<List<CourseEntity>> getAllCourses();

    // Untuk Halaman Detail
    LiveData<CourseEntity> getCourseWithModules(String courseId);

    // Untuk Halaman Reader (fragment list + fragment content)
    LiveData<List<ModuleEntity>> getAllModulesByCourse(String courseId);

    // Untuk halaman bookmark (dipakai di modul 3 room (sebelum paging))
    LiveData<List<CourseEntity>> getBookmarkedCourses();

    // Untuk mendapatkan detailContent
    LiveData<ModuleEntity> getContent(String courseId, String moduleId);
}
