package com.dicoding.academies.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.ModuleEntity

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId: String? = null
    var moduleId: String? = null

    fun getModules(): LiveData<List<ModuleEntity>> {
        return academyRepository.getAllModulesByCourse(courseId)
    }

    fun getSelectedModule(): LiveData<ModuleEntity> {
        return academyRepository.getContent(courseId, moduleId)
    }
}

