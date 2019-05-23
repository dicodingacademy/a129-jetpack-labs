package com.dicoding.academies.data.source;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;

import java.util.List;

public interface AcademyDataSource {

    // Untuk Halaman Academy
    List<CourseEntity> getAllCourses();

    // Untuk Halaman Detail
    CourseEntity getCourseWithModules(String courseId);

    // Untuk Halaman Reader (fragment list + fragment content)
    List<ModuleEntity> getAllModulesByCourse(String courseId);

    // Untuk halaman bookmark (dipakai di modul 3 room (sebelum paging))
    List<CourseEntity> getBookmarkedCourses();

    // Untuk mendapatkan detailContent
    ModuleEntity getContent(String courseId, String moduleId);
}
