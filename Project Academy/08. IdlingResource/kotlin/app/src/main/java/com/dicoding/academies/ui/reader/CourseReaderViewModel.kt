package com.dicoding.academies.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.ModuleEntity

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId: String = ""
    var moduleId: String = ""

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> {
        return academyRepository.getAllModulesByCourse(courseId)
    }

    fun getSelectedModule(): LiveData<ModuleEntity> {
        return academyRepository.getContent(courseId, moduleId)
    }
}

