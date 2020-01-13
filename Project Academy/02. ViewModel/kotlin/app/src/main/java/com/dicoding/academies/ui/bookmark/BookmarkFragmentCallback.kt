package com.dicoding.academies.ui.bookmark


import com.dicoding.academies.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

