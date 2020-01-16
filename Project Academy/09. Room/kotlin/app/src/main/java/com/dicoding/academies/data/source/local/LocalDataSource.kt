package com.dicoding.academies.data.source.local

import androidx.lifecycle.LiveData

import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.CourseWithModule
import com.dicoding.academies.data.source.local.entity.ModuleEntity
import com.dicoding.academies.data.source.local.room.AcademyDao

class LocalDataSource private constructor(private val mAcademyDao: AcademyDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: AcademyDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(academyDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllCourses(): LiveData<List<CourseEntity>> = mAcademyDao.getCourses()

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>> = mAcademyDao.getBookmarkedCourse()

    fun getCourseWithModules(courseId: String): LiveData<CourseWithModule> =
            mAcademyDao.getCourseWithModuleById(courseId)

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> =
            mAcademyDao.getModulesByCourseId(courseId)

    fun insertCourses(courses: List<CourseEntity>) = mAcademyDao.insertCourses(courses)

    fun insertModules(modules: List<ModuleEntity>) = mAcademyDao.insertModules(modules)

    fun setCourseBookmark(course: CourseEntity, newState: Boolean) {
        course.bookmarked = newState
        mAcademyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> =
            mAcademyDao.getModuleById(moduleId)

    fun updateContent(content: String, moduleId: String) {
        mAcademyDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.read = true
        mAcademyDao.updateModule(module)
    }
}