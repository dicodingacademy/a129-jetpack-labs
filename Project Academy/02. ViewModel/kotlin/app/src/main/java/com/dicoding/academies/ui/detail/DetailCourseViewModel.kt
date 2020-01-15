package com.dicoding.academies.ui.detail

import androidx.lifecycle.ViewModel

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.data.ModuleEntity
import com.dicoding.academies.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    var courseId: String = ""
    private var course: CourseEntity? = null

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        for(course in DataDummy.generateDummyCourses()) {
            if(course.courseId == courseId) {
                this.course = course
            }
        }
        return course as CourseEntity
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}


