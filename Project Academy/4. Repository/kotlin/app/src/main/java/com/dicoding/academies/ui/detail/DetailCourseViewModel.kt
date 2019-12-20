package com.dicoding.academies.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    var courseId: String? = null

    fun getCourse(): CourseEntity {
        return academyRepository.getCourseWithModules(courseId)
    }

    fun getModules(): List<ModuleEntity> {
        return academyRepository.getAllModulesByCourse(courseId)
    }
}


