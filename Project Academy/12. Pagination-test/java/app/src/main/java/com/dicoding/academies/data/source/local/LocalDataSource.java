package com.dicoding.academies.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.local.room.AcademyDao;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final AcademyDao mAcademyDao;

    private LocalDataSource(AcademyDao mAcademyDao) {
        this.mAcademyDao = mAcademyDao;
    }

    public static LocalDataSource getInstance(AcademyDao academyDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(academyDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, CourseEntity> getAllCourses() {
        return mAcademyDao.getCourses();
    }

    public DataSource.Factory<Integer, CourseEntity> getBookmarkedCourses() {
        return mAcademyDao.getBookmarkedCourse();
    }

    public LiveData<CourseWithModule> getCourseWithModules(final String courseId) {
        return mAcademyDao.getCourseWithModuleById(courseId);
    }

    public LiveData<List<ModuleEntity>> getAllModulesByCourse(String courseId) {
        return mAcademyDao.getModulesByCourseId(courseId);
    }

    public void insertCourses(List<CourseEntity> courses) {
        mAcademyDao.insertCourses(courses);
    }

    public void insertModules(List<ModuleEntity> modules) {
        mAcademyDao.insertModules(modules);
    }

    public void setCourseBookmark(CourseEntity course, boolean newState) {
        course.setBookmarked(newState);
        mAcademyDao.updateCourse(course);
    }

    public LiveData<ModuleEntity> getModuleWithContent(String moduleId) {
        return mAcademyDao.getModuleById(moduleId);
    }

    public void updateContent(String content, String moduleId) {
        mAcademyDao.updateModuleByContent(content, moduleId);
    }

    public void setReadModule(final ModuleEntity module) {
        module.setRead(true);
        mAcademyDao.updateModule(module);
    }
}