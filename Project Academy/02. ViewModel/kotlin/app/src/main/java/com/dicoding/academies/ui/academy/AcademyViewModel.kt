package com.dicoding.academies.ui.academy

import androidx.lifecycle.ViewModel

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}

