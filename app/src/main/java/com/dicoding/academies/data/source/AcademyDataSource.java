package com.dicoding.academies.data.source;


import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.vo.Resource;

import java.util.List;

public interface AcademyDataSource {

    // Untuk Halaman Academy
    LiveData<Resource<List<CourseEntity>>> getAllCourses();

    // Untuk Halaman Detail
    LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId);

    // Untuk Halaman Reader (fragment list + fragment content)
    LiveData<Resource<List<ModuleEntity>>> getAllModulesByCourse(String courseId);

    // Untuk Halaman Bookmark
    // LiveData<Resource<List<CourseEntity>>> getBookmarkedCourses();
    LiveData<Resource<PagedList<CourseEntity>>> getBookmarkedCoursesPaged();

    // Untuk getContent
    LiveData<Resource<ModuleEntity>> getContent(String moduleId);

    // Untuk halaman detail, set bookmark
    void setCourseBookmark(CourseEntity course, boolean state);

    // Untuk halaman reader, read module
    void setReadModule(ModuleEntity module);
}