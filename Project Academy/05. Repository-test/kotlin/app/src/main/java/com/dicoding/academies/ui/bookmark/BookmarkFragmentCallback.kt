package com.dicoding.academies.ui.bookmark


import com.dicoding.academies.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

