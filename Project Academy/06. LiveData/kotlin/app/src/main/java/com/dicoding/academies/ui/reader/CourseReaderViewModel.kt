package com.dicoding.academies.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.ModuleEntity

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    lateinit var courseId: String
    lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> =
            academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> =
            academyRepository.getContent(courseId, moduleId)
}

