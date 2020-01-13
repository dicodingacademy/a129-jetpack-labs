package com.dicoding.academies.data;


import androidx.lifecycle.LiveData;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.vo.Resource;

import java.util.List;

public interface AcademyDataSource {

    LiveData<Resource<List<CourseEntity>>> getAllCourses();

    LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId);

    LiveData<Resource<List<ModuleEntity>>> getAllModulesByCourse(String courseId);

    LiveData<Resource<ModuleEntity>> getContent(String moduleId);

    LiveData<List<CourseEntity>> getBookmarkedCourses();

    void setCourseBookmark(CourseEntity course, boolean state);

    void setReadModule(ModuleEntity module);
}