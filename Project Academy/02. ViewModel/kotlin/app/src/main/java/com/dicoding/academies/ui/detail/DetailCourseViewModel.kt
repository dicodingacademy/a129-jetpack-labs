package com.dicoding.academies.ui.detail

import androidx.lifecycle.ViewModel

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.data.ModuleEntity
import com.dicoding.academies.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity? {
        var course: CourseEntity? = null
        for (courseEntity in DataDummy.generateDummyCourses()) {
            if (courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }
        return course
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}


