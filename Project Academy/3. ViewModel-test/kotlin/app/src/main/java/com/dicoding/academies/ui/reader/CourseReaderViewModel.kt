package com.dicoding.academies.ui.reader

import androidx.lifecycle.ViewModel

import com.dicoding.academies.data.ContentEntity
import com.dicoding.academies.data.ModuleEntity
import com.dicoding.academies.utils.DataDummy

import java.util.ArrayList

class CourseReaderViewModel : ViewModel() {

    var courseId: String? = null
    var moduleId: String? = null

    fun getModules(): ArrayList<ModuleEntity> {
        return DataDummy.generateDummyModules(courseId)
    }

    fun getSelectedModule(): ModuleEntity {
        var module: ModuleEntity? = null
        for (i in 0 until getModules().size) {
            if (getModules()[i].moduleId == moduleId) {
                module = getModules()[i]
                module.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + module.title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
                break
            }
        }
        return module as ModuleEntity
    }
}

