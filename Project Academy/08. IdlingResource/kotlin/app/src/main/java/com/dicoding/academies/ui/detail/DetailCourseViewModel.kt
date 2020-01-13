package com.dicoding.academies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    var courseId: String = ""

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> {
        return academyRepository.getCourseWithModules(courseId)
    }

    fun getModules(): LiveData<List<ModuleEntity>> {
        return academyRepository.getAllModulesByCourse(courseId)
    }
}


