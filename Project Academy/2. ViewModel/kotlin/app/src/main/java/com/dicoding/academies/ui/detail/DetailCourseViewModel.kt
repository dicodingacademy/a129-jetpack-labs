package com.dicoding.academies.ui.detail

import androidx.lifecycle.ViewModel

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.data.ModuleEntity
import com.dicoding.academies.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    var courseId: String? = null
    private var course: CourseEntity? = null

    fun getCourse(): CourseEntity {
        for (i in 0 until DataDummy.generateDummyCourses().size) {
            val courseEntity = DataDummy.generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }
        return course as CourseEntity
    }

    fun getModules(): List<ModuleEntity> {
        return DataDummy.generateDummyModules(courseId)
    }
}


